/**
 * 
 */
package com.bbva.kltt.benchmark.exec;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.benchmark.exception.BenchmarkException;
import com.bbva.kltt.benchmark.model.TestData;

/**
 * Execute the tests based on their dependencies
 * 
 * @author psm
 */
public class TestsManager
{
	/** LOGGER */
	private static final Logger LOGGER = LoggerFactory.getLogger(TestsManager.class);

	/** Number of finished tests */
	private int numFinishedTests = 0;

	/** Executor */
	private final Executor exec = Executors.newCachedThreadPool();

	/** CompletationService stores the result in a blocking queue */
	private final CompletionService<WorkerResult<String>> ecs = new ExecutorCompletionService<WorkerResult<String>>(exec);

	/** Test list */
	private final List<Test> tests = new ArrayList<Test>();

	/** Time unit used (MILLI/NANO) */
	private final TimeUnit timeUnit;

	/** Indicates if the statistics should be exported to CSV format */
	private final boolean exportCSV;

	/** Indicates if the statistics should be exported to JSON format */
	private final boolean exportJSON;

	/**
	 * Constructor
	 * 
	 * @param testsData Test Data List
	 * @param timeUnit Time unit
	 * @param exportJSON Indicates if statistics should be exported to JSON format
	 * @param exportCSV Indicates if statistics should be exported to CSV format
	 */
	public TestsManager(final List<TestData> testsData, final TimeUnit timeUnit, final boolean exportCSV, final boolean exportJSON)
	{
		this.timeUnit = timeUnit;
		this.exportCSV = exportCSV;
		this.exportJSON = exportJSON;
		buildTests(testsData); 
		
		
	}

	/**
	 * Buld tests
	 * 
	 * @param testsData Test data list
	 */
	private void buildTests (final List<TestData> testsData)
	{
		for( TestData testData: testsData )
		{
			tests.add(new Test(testData, this.timeUnit, this.exportCSV, this.exportJSON));
		}
	}

	/**
	 * Execute the tests based on their dependencies
	 * 
	 * @throws BenchmarkException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute () throws BenchmarkException
	{
		LOGGER.debug("Total tests ({}). Num Finished tests ({})", tests.size(), this.numFinishedTests);

		for (Test test : tests)
		{
			// If this test was not invoked and it is independent then
			// submit it
			if (!test.isInvoked() && test.isIndependant())
			{
				test.setInvoked(true);
				ecs.submit((Callable) test);
			}
		}
		checkFinished();
	}

	/**
	 * Checks finished works. When a work finishes then send an event to the not
	 * invoked ones and they will check its dependencies.
	 * 
	 * @throws BenchmarkException
	 */
	public void checkFinished () throws BenchmarkException
	{
		while (this.numFinishedTests < tests.size())
		{
			try
			{
				WorkerResult<String> wResult = ecs.take().get();
				if (wResult != null)
				{
					// New test finished
					this.numFinishedTests++;
					sendWorkerFinished(wResult);
					// A new Test was finished, new independent should be
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
	 * Sends a finish event to not finished tests
	 * 
	 * @param wResult Contains the test result
	 */
	public void sendWorkerFinished (final WorkerResult<String> wResult)
	{
		LOGGER.info("({}) Test finished ... sending event", wResult.getId());
		for (Test test : this.tests)
		{
			if (!test.isInvoked())
			{
				test.onFinished(wResult);
			}
		}
	}

}
