/**
 * 
 */
package com.bbva.kltt.benchmark.exec;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.benchmark.api.TestContext;
import com.bbva.kltt.benchmark.model.TestData;
import com.bbva.kltt.benchmark.util.ITimeUtil;
import com.bbva.kltt.benchmark.util.AbstractTimeFactory;

/**
 * @author psm
 * 
 */
public class Test extends AbstractDependantWorker
{

	/** LOGGER */
	private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

	/** Test data associated to this Test */
	private TestData testData;

	/** Time unit */
	private final TimeUnit timeUnit;

	/** Export to CSV */
	private final boolean exportCSV;

	/** Export to JSON */
	private final boolean exportJSON;

	/**
	 * Constructor
	 * 
	 * @param testData Test data
	 * @param timeUnit Time unit for measures
	 * @param exportCSV Indicates if the measures will be exported in csv format
	 * @param exportJSON Indicates if the measures will be exported in json format
	 */
	public Test(final TestData testData, final TimeUnit timeUnit, final boolean exportCSV, final boolean exportJSON)
	{
		super();
		this.testData = testData; 
		this.timeUnit = timeUnit;
		this.exportCSV = exportCSV;
		this.exportJSON = exportJSON;
	}

	@Override
	public WorkerResult<String> call () throws Exception
	{
		LOGGER.debug("Executing Test ({}), ({})", this.testData.getId(), this.testData.getName());
		WorkerResult<String> res = new WorkerResult<String>();
		res.setId(this.testData.getId());
		
		ITimeUtil timeUtil = AbstractTimeFactory.getTimeUtil(this.timeUnit);

		TestContext testContext = new TestContext(
				this.testData.getInitialization().getFile(),
					this.testData.getInitialization().getBeanNames(),
					this.testData.getName(),
					timeUtil);

		JobsManager aManager = new JobsManager(testContext, this.testData.getJobsData());

		long start = timeUtil.getTime();
		aManager.execute();
		long end = timeUtil.getTime();

		testContext.stop();
		testContext.getMeasures().addMeasure(this.testData.getName(), end - start);
		exportData(testContext);
		return res;
	}

	/**
	 * Export the data to JSON and/or CSV format, depending on the configuration
	 * 
	 * @param testContext Test context
	 */
	private void exportData (final TestContext testContext)
	{
		testContext.getMeasures().generateStatistics();
		if (this.exportJSON)
		{
			testContext.getMeasures().exportJSON(this.testData.getName());
		}
		if (this.exportCSV)
		{
			testContext.getMeasures().exportCSV(this.testData.getName());
		}
	}

	@Override
	public Map<Integer, String> getDepends ()
	{
		return testData.getDepends();
	}

	/**
	 * Gets test data
	 * 
	 * @return the testData
	 */
	public TestData getTestData ()
	{
		return testData;
	}

	/**
	 * Sets test data
	 * 
	 * @param testData the testData to set
	 */
	public void setTestData (final TestData testData)
	{
		this.testData = testData;
	}

}
