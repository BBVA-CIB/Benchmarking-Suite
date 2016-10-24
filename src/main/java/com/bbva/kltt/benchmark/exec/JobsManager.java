/**
 * 
 */
package com.bbva.kltt.benchmark.exec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.benchmark.api.TestContext;
import com.bbva.kltt.benchmark.exception.BenchmarkException;
import com.bbva.kltt.benchmark.model.JobData;
import com.bbva.mon.performancemonitor.api.MeasuresCachePerId;

/**
 * This class execute the jobs as they are declared. It creates the needed
 * instances and manages jobs termination
 * 
 * @author psm
 */
public class JobsManager
{
	/** LOGGER */
	private static final Logger LOGGER = LoggerFactory.getLogger(JobsManager.class);

	/** Job list */
	private final List<Job> jobs = new ArrayList<Job>();

	/** Executor thread pool. It will reuse the threads when they are free */
	private final Executor exec = Executors.newCachedThreadPool();

	/**
	 * CompletitionService stores the result in a blocking queue and this allows
	 * to check for finished works
	 */
	private final CompletionService<WorkerResult<?>> ecs = new ExecutorCompletionService<WorkerResult<?>>(exec);

	/** Map that stores the number of instances per job */
	private final Map<Integer, Integer> instancesPerJob = new HashMap<Integer, Integer>();

	/** Map that stores the number of finished instances per job */
	private final Map<Integer, Integer> finishedInstancesPerJob = new HashMap<Integer, Integer>();

	/** Number of finished jobs ( all its instances finished ) */
	private int numFinishedJobs;

	/** Indicates if the execution should stop */
	private boolean stop = false;

	/** Test context */
	private final TestContext testContext;

	/** Job measures. Contains Measures for all the job instances */
	private final MeasuresCachePerId jobMeasures;

	/**
	 * Constructor
	 * 
	 * @param testContext Test context
	 * @param jobsData Job data list
	 */
	public JobsManager(final TestContext testContext, final List<JobData> jobsData)
	{
		this.testContext = testContext;
		//Store the job measures
		this.jobMeasures = new MeasuresCachePerId();
		buildJobs(jobsData);
	}

	/**
	 * @param jobsData
	 */
	private void buildJobs (final List<JobData> jobsData)
	{
		for (JobData jobData : jobsData)
		{
			this.jobs.add(new Job(jobData, this.testContext, 0, this.jobMeasures.getMeasures(0)));
		}
	}

	/**
	 * Execute all jobs in the list, creating the needed instances. Checks
	 * dependencies
	 * 
	 * @throws BenchmarkException
	 * @throws InterruptedException
	 */
	public void execute () throws BenchmarkException, InterruptedException
	{
		LOGGER.debug("Total jobs ({}). Num Finished jobs ({})", jobs.size(), this.numFinishedJobs);
		if (this.numFinishedJobs == jobs.size())
		{
			this.stop = true;
			return;
		}
		
		for (Job job : this.jobs)
		{
			// If this job was not invoked and it is independent then
			// submit it
			if (!job.isInvoked() && job.isIndependant())
			{
				job.setInvoked(true);
				job.setTestContext(this.testContext);

				instancesPerJob.put(job.getJobData().getId(), job.getJobData().getInstances());

				LOGGER.debug("This job ({}) has ({}) instances. Creating instances ...",
						job.getJobData().getId(),
						job.getJobData().getInstances());
				for (int i = 0; i < job.getJobData().getInstances(); i++)
				{
					ecs.submit(new Job(job.getJobData(), this.testContext, i, jobMeasures.getMeasures(i)));
				}
			}
		}
		checkFinished();
	}

	/**
	 * Checks the finished jobs
	 * 
	 * @throws BenchmarkException
	 */
	public void checkFinished () throws BenchmarkException
	{
		while (!this.stop)
		{
			try
			{
				// Wait for another finished job
				WorkerResult<?> res = ecs.take().get();

				int jobId = res.getId();
				incFinishedInstances(jobId);

				// If all the instance of this job are finished then the job is
				// finished
				if (this.instancesPerJob.get(jobId).longValue() == this.finishedInstancesPerJob.get(jobId).longValue())
				{
					this.numFinishedJobs++;
					sendWorkerFinished(res);
					// A new Test was finished, new independant should be
					// checked
					execute();
				}

			}
			catch (InterruptedException e)
			{
				throw new BenchmarkException("Exception in test", e);
			}
			catch (ExecutionException e)
			{
				throw new BenchmarkException("Exception in test", e);
			}
		}
	}

	/**
	 * Increments the number of finished instances for indicated job
	 * 
	 * @param jobId The job with one more instance finished
	 */
	private void incFinishedInstances (final int jobId)
	{
		if (this.finishedInstancesPerJob.get(jobId) == null)
		{
			this.finishedInstancesPerJob.put(jobId, 1);
		}
		else
		{
			// Inc num finished instances for this job
			this.finishedInstancesPerJob.put(jobId, finishedInstancesPerJob.get(jobId) + 1);
		}
	}

	/**
	 * Sends a finish event to not finished tests
	 * 
	 * @param wResult Contains the test result
	 */
	public void sendWorkerFinished (final WorkerResult<?> wResult)
	{
		LOGGER.info("This job is finished ... sending event job ({})", wResult.getId());
		for (Job job : this.jobs)
		{
			if (!job.isInvoked())
			{
				job.onFinished(wResult);
			}
		}
	}
}
