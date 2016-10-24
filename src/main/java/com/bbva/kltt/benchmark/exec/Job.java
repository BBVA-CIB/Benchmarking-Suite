/**
 * 
 */
package com.bbva.kltt.benchmark.exec;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.benchmark.api.TestContext;
import com.bbva.kltt.benchmark.model.JobData;

/**
 * Class that executes the job
 * 
 * @author psm
 * 
 */
public class Job extends AbstractDependantWorker
{
	/** LOGGER */
	private static final Logger LOGGER = LoggerFactory.getLogger(Job.class);

	/** Job data associated to this job */
	private final JobData jobData;

	/** Test context */
	private TestContext testContext;

	/** Instance id */
	private int instanceId;

	/** Contains the measures for this instance */
	private final List<Long> partialMeasures;

	/**
	 * Constructor
	 * 
	 * @param jobData Data associated
	 * @param testContext Test context
	 * @param intanceId The instance identifier
	 * @param partialMeasures Measures associated to this job instance
	 */
	public Job(final JobData jobData, final TestContext testContext, final int intanceId, final List<Long> partialMeasures)
	{
		super();
		this.jobData = jobData;
		this.testContext = testContext;
		this.instanceId = intanceId;
		this.partialMeasures = partialMeasures;
	}

	@Override
	public WorkerResult<String> call () throws Exception
	{
		LOGGER.info("Executing Job ({})", this.getJobData().getId());

		WorkerResult<String> result = new WorkerResult<String>();
		result.setId(this.getJobData().getId());

		// Execute actions
		ActionsManager actionMgr = new ActionsManager(this.testContext, this, this.jobData.getActionsData(), this.partialMeasures);

		long start = testContext.getTimeUtil().getTime();
		actionMgr.execute();
		long end = testContext.getTimeUtil().getTime();
		
		// This is the global measures for the complete job instance
		testContext.getMeasures().addMeasure(this.testContext.getTestName() + this.jobData.getName() + "_FULLJOBINST", end-start);
		result.setId(this.jobData.getId());
		return result;
	}
	
	/**
	 * Set test context
	 * 
	 * @param testContext Test context to set
	 */
	public void setTestContext (final TestContext testContext)
	{
		this.testContext = testContext;

	}

	@Override
	public Map<Integer, String> getDepends ()
	{
		return this.jobData.getDepends();
	}

	/**
	 * Gets the jobData
	 * 
	 * @return the jobData
	 */
	public JobData getJobData ()
	{
		return jobData;
	}

	/**
	 * Gets instance id
	 * 
	 * @return the intanceId
	 */
	public int getIntanceId ()
	{
		return instanceId;
	}

	/**
	 * Sets intance id
	 * 
	 * @param intanceId the intanceId to set
	 */
	public void setIntanceId (final int intanceId)
	{
		this.instanceId = intanceId;
	}

}
