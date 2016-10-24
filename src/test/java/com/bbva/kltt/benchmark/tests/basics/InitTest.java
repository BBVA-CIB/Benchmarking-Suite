/**
 * 
 */
package com.bbva.kltt.benchmark.tests.basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.bbva.kltt.benchmark.api.ITestInit;
import com.bbva.kltt.benchmark.exception.BenchmarkException;
import com.bbva.kltt.benchmark.tests.basics.action.CacheActions;



/**
 * @author psm
 * 
 */

public class InitTest implements ITestInit
{
	private static final Logger LOGGER = LoggerFactory.getLogger(InitTest.class);
	
	public InitTest()
	{
		System.out.println("HOLOOOOOOOO");
	}
	@Override
	public void init (ApplicationContext context, String testName) throws BenchmarkException
	{
		CacheActions.getInstance().addInit(testName);
		LOGGER.info("Initializing ({})", testName);
	}

	@Override
	public void stop (String testName) throws BenchmarkException
	{
		LOGGER.info("Stopping ({})", testName);
	}
}
