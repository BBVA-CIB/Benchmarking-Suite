/**
 * 
 */
package com.bbva.kltt.benchmark.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.bbva.kltt.benchmark.exception.BenchmarkException;
import com.bbva.kltt.benchmark.util.ITimeUtil;
import com.bbva.mon.performancemonitor.api.MeasuresCache;
import com.bbva.mon.performancemonitor.api.PerformanceMonitor;

/**
 * This class represents the context used by an specific test.
 * 
 * @author psm
 * 
 */
public class TestContext
{
	/** Test name */
	private String testName;

	/** TimeUtil used for measures. It could be Milliseconds or Nanoseconds */
	private ITimeUtil timeUtil;

	/** Initializables */
	private final Map<String, ITestInit> initializables = new HashMap<String, ITestInit>();

	/** Spring context */
	private ApplicationContext springContext;

	/** Spring file */
	private final String springFile;

	/** Measures cache */
	private final MeasuresCache measures = PerformanceMonitor.getMeasuresCache();

	/**
	 * Constructor
	 * 
	 * @param springFile Spring file to use
	 * @param initBeansNames Initialization beans associated to the test
	 * @param testName Test name
	 * @param timeUtil Time unit used during measures
	 * @throws BenchmarkException If there is a problem durin initialization
	 */
	public TestContext (final String springFile, final List<String> initBeansNames,
			final String testName,
			final ITimeUtil timeUtil) throws BenchmarkException
	{
		this.springFile = springFile;
		this.testName = testName;
		this.timeUtil = timeUtil;
		initBeans(initBeansNames);
	}

	/**
	 * Execute initializations
	 * 
	 * @param initBeansNames Init beans names
	 * @throws BenchmarkException
	 */
	private void initBeans (final List<String> initBeansNames) throws BenchmarkException
	{
		this.springContext = new FileSystemXmlApplicationContext(this.springFile);
		for (String bean : initBeansNames)
		{
			ITestInit initInstance = springContext.getBean(bean, ITestInit.class);
			initInstance.init(springContext, testName);
			initializables.put(bean, springContext.getBean(bean, ITestInit.class));
		}
	}

	/**
	 * Invoke the stop on the initialization test when it finished to liberate resources
	 * 
	 * @throws BenchmarkException If there is a problem during stop
	 */
	public void stop () throws BenchmarkException
	{
		for (String beanName : this.initializables.keySet())
		{
			ITestInit testInit = this.initializables.get(beanName);
			testInit.stop(this.testName);
		}
	}

	/**
	 * Gets the test name
	 * 
	 * @return Test name
	 */
	public String getTestName ()
	{
		return this.testName;
	}

	/**
	 * Sets the test name
	 * 
	 * @param testName Test name to set
	 */
	public void setTestName (final String testName)
	{
		this.testName = testName;
	}

	/**
	 * Gets initializables
	 * 
	 * @return the initializables interfaces by bean name
	 */
	public Map<String, ITestInit> getInitializables ()
	{
		return this.initializables;
	}

	/**
	 * Gets Spring context
	 * 
	 * @return the springContext
	 */
	public ApplicationContext getSpringContext ()
	{
		return this.springContext;
	}

	/**
	 * Gets time unit
	 * 
	 * @return the timeUnit
	 */
	public ITimeUtil getTimeUtil ()
	{
		return this.timeUtil;
	}

	/**
	 * Sets time unit
	 * 
	 * @param timeUtil the timeUnit to set
	 */
	public void setTimeUtil (final ITimeUtil timeUtil)
	{
		this.timeUtil = timeUtil;
	}

	/**
	 * Gets measures object
	 * 
	 * @return the measures
	 */
	public MeasuresCache getMeasures ()
	{
		return this.measures;
	}

}
