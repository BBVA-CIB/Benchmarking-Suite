package com.bbva.kltt.benchmark;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.benchmark.exception.BenchmarkException;
import com.bbva.kltt.benchmark.exec.TestsManager;
import com.bbva.kltt.benchmark.model.Suite;
import com.bbva.kltt.benchmark.util.ITimeUtil;
import com.bbva.kltt.benchmark.util.AbstractTimeFactory;
import com.bbva.mon.performancemonitor.api.MeasuresCache;
import com.bbva.mon.performancemonitor.api.PerformanceMonitor;
import com.bbva.mon.performancemonitor.exception.PerformanceMonitorException;

/**
 * Main class, it loads the test file and execute the tests.
 * 
 */
public final class Benchmark
{
	/** LOGGER */
	private static final Logger LOGGER = LoggerFactory.getLogger(Benchmark.class);

	/** Suite name constant */
	private static final String SUITETIME = "SUITETIME";

	/** Measures */
	private final MeasuresCache measures = PerformanceMonitor.getMeasuresCache();

	/**
	 * Main class
	 * 
	 * @param args The file name to process
	 * 
	 * @throws BenchmarkException If there is problem during testing
	 * @throws PerformanceMonitorException
	 */
	public static void main (final String[] args) throws BenchmarkException, PerformanceMonitorException
	{
		LOGGER.info("Benchmark. Version ({})", Version.getVersionNumber());
		if (args == null || args.length != 1 || args[0] == null)
		{
			LOGGER.error("Usage: Benchmarck testsFile.xml");
		}
		else
		{
			Benchmark bench = new Benchmark();
			Suite suite = bench.loadFile(args[0]);
			bench.start(suite);
		}
	}

	/**
	 * Starts the test execution
	 * 
	 * @param suite Suite to execute
	 * @throws BenchmarkException If there is a problem during suite execution
	 * @throws PerformanceMonitorException If there is a problem during performance monitoring measures
	 * 
	 */
	public void start (final Suite suite) throws BenchmarkException, PerformanceMonitorException
	{
		LOGGER.info("Start Suite"); 
		
		ITimeUtil timeUtil = AbstractTimeFactory.getTimeUtil(suite.getTimeUnit());

		long start = timeUtil.getTime(); 

		LOGGER.info("MEMORY REPORT START ({})", PerformanceMonitor.getMemoryPerformanceMonitor().getMemoryReport());

		TestsManager dependantMgr = new TestsManager(
				suite.getTestsData(),
					suite.getTimeUnit(),
					suite.isExportCSV(),
					suite.isExportJSON());
		dependantMgr.execute();

		long end = timeUtil.getTime();

		measures.addMeasure(SUITETIME, end - start);
		measures.print();
		long heap = PerformanceMonitor.getMemoryPerformanceMonitor().getHeapMemory();
		LOGGER.info("HEAP MEMORY ({} MB)", heap);
		LOGGER.info("MEMORY REPORT END ({})", PerformanceMonitor.getMemoryPerformanceMonitor().getMemoryReport());

		LOGGER.info("End Suite");
	}

	/**
	 * Loads the test file
	 * 
	 * @param pathFile the path to the test files
	 * @return Suite based on the indicated file
	 * @throws BenchmarkException If there is a problem building the suite
	 */
	public Suite loadFile (final String pathFile) throws BenchmarkException
	{
		LOGGER.info("Loading file from: " + pathFile);
		JAXBContext context;
		try
		{
			context = JAXBContext.newInstance(Suite.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Suite suite = (Suite) unmarshaller.unmarshal(new File(pathFile));
			LOGGER.debug(suite.toString());
			return suite;
		}
		catch (JAXBException e)
		{
			throw new BenchmarkException("It is not possible to load tests file from: " + pathFile, e);
		}

	}
}