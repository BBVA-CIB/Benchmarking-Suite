/**
 * 
 */
package com.bbva.kltt.benchmark.exec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.benchmark.api.ActionResult;
import com.bbva.kltt.benchmark.api.IAction;
import com.bbva.kltt.benchmark.api.ICondition;
import com.bbva.kltt.benchmark.api.JobContext;
import com.bbva.kltt.benchmark.api.TestContext;
import com.bbva.kltt.benchmark.model.ActionData;
import com.bbva.kltt.benchmark.model.JobData;
import com.bbva.kltt.benchmark.model.adapters.ConditionScope;
import com.bbva.kltt.benchmark.util.ITimeUtil;
import com.bbva.mon.performancemonitor.api.MeasuresCache;
import com.bbva.mon.performancemonitor.api.PerformanceMonitor;

/**
 * This class manage the actions execution. It will execute them based on the
 * properties defined in the benchmark file.
 * 
 * @author psm
 * 
 */
public class ActionsManager
{
	/** LOGGER */
	private static final Logger LOGGER = LoggerFactory.getLogger(ActionsManager.class);

	/** Action list */
	private final List<Action> actions = new ArrayList<Action>();

	/** Test context */
	private final TestContext testContext;

	/** Indicates if the execution should stop */
	private boolean stop = false;

	/** Parent job */
	private final Job job;
	
	/** JobData */
	private final JobData jobData;
	
	/** Is periodic */
	private final boolean periodic;
	
	/** Period */
	private final long period;
	
	/** Condition scope */
	private final ConditionScope conditionScope;

	/** Global condition ( for all actions ) */
	private ICondition<?> globalCondition;

	/** Partial measures. Store the iterations measures */
	private final List<Long> iterationsMeasures;

	/** User measures. Stores measures defined by the user */
	MeasuresCache userMeasures = PerformanceMonitor.getMeasuresCache();

	/**
	 * Constructor
	 * 
	 * @param testContext Test context
	 * @param job Parent job
	 * @param actionsData List of actions data associated to indicated job
	 * @param partialMeasures Partial measures
	 */
	public ActionsManager(final TestContext testContext, final Job job, final List<ActionData> actionsData,
			final List<Long> partialMeasures)
	{
		this.testContext = testContext;
		this.job = job;
		this.jobData = job.getJobData();
		this.periodic = this.jobData.isPeriodic();
		this.period = this.jobData.getPeriod();
		this.conditionScope = this.jobData.getConditionScope();
		this.iterationsMeasures = partialMeasures;
		buildActions(actionsData);
	}

	/**
	 * Build actions 
	 * 
	 * @param actionsData Actions data list
	 */
	private void buildActions (final List<ActionData> actionsData)
	{
		for (ActionData actionData : actionsData)
		{
			this.actions.add(new Action(actionData));
		}
	}

	/**
	 * Execute all actions
	 * 
	 * @throws InterruptedException If there is a problem
	 */
	public void execute () throws InterruptedException
	{
		Map<Integer, IAction<?>> testables = buildTests();

		ActionResult<?> testResult = null;

		ITimeUtil timeUtil = this.testContext.getTimeUtil();

		setCondition();

		int iter = 0;
		JobContext jobContext = new JobContext();
		jobContext.setUserMeasures(this.userMeasures);
		// apply delay
		Thread.sleep(this.jobData.getDelay());
		while (!this.stop)
		{
			jobContext.setJobIteration(iter++);
			long start = 0;
			// Stores the measures if it is configured
			if (this.jobData.isMeasures())
			{
				start = timeUtil.getTime();
			}
			if (matchCondition(this.globalCondition, testResult, ConditionScope.JOB))
			{
				for (Action action : this.actions)
				{
					if (matchCondition(action.getCondition(), testResult, ConditionScope.ACTION))
					{
						IAction<?> iaction = testables.get(action.getActionData().getId());

						// Call test ... the real action
						// -------------------------------
						testResult = iaction.execute(this.testContext, jobContext);
						// -------------------------------
					}
					else
					{
						conditionNotMatch();
						break;
					}
				}

				// Stores the measures if it is configured
				if (this.jobData.isMeasures())
				{
					long end = timeUtil.getTime();
					this.iterationsMeasures.add(end - start);
				}
				managePeriodicity();
			}
			else
			{
				conditionNotMatch();
			}

		}
		// Add iteration measures, if any
		if (this.jobData.isMeasures())
		{
			this.testContext.getMeasures().addMeasure(this.testContext.getTestName() + this.jobData.getName()
					+ "_JOBITER",
					this.iterationsMeasures);
		}

		// Add user measures to the global ones
		this.testContext.getMeasures().merge(userMeasures.generateFinalMeasures());

	}

	/**
	 * Checks if the condition match
	 */
	private void conditionNotMatch ()
	{
		LOGGER.info("({}) Finished, condition does not match. Stop", this.jobData.getId());
		this.stop = true;
	}

	/**
	 * Sets the condition to each action
	 */
	private void setCondition ()
	{
		for (Action action : this.actions)
		{
			action.setCondition(getCondition());
		}
	}

	/**
	 * Creates a new condition instance per action or reuse the same one
	 * depending on defined scope
	 * 
	 * @return The condition
	 */
	private ICondition<?> getCondition ()
	{
		ICondition<?> res = null;
		if (job.getJobData().getConditionBean() != null)
		{
			if (this.conditionScope == ConditionScope.ACTION)
			{
				res = testContext.getSpringContext().getBean(job.getJobData().getConditionBean(), ICondition.class);
			}
			else
			{
				if (this.globalCondition == null)
				{
					this.globalCondition = testContext.getSpringContext().getBean(job.getJobData().getConditionBean(),
							ICondition.class);
				}
				res = this.globalCondition;
			}
		}
		return res;
	}

	/**
	 * If there is a period defined then it will wait for the indicate time, in
	 * other case the execution will stop.
	 * 
	 * @throws InterruptedException
	 */
	private void managePeriodicity () throws InterruptedException
	{
		if (this.periodic)
		{
			// Period wait
			if (this.period > 0)
			{
				Thread.sleep(this.period);
			}			
		}
		else
		{
			// This is not a periodic job, then execute it just once
			LOGGER.info("({}) Job not periodic, just one execution. Stop", this.jobData.getId());
			this.stop = true;
		}
	}

	/**
	 * It creates the test bean instance per each action
	 * 
	 * @return A map with the associated test instance per action id
	 */
	private Map<Integer, IAction<?>> buildTests ()
	{
		Map<Integer, IAction<?>> tests = new HashMap<Integer, IAction<?>>();
		for (Action action : actions)
		{
			String beanName = action.getActionData().getBeanName();
			IAction<?> test = this.testContext.getSpringContext().getBean(beanName, IAction.class);
			tests.put(action.getActionData().getId(), test);
		}
		return tests;
	}

	/**
	 * Checks the condition. If the condition does not match then the execution
	 * will stop
	 * 
	 * @param condition Condition to check
	 * @param testResult Test result
	 * @param scope Condition scope
	 * 
	 * @return True if matches the condition, false otherwise
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean matchCondition (final ICondition condition,
									final ActionResult<?> testResult,
									final ConditionScope scope)
	{
		boolean conditionRes = true;
		if (condition != null && this.conditionScope == scope)
		{
			conditionRes = condition.check(testResult == null ? null : testResult.getResult());
		}
		return conditionRes;
	}
}
