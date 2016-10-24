/**
 * 
 */
package com.bbva.kltt.benchmark.api;

import org.springframework.context.ApplicationContext;

import com.bbva.kltt.benchmark.exception.BenchmarkException;

/**
 * Interface that must implement any Initialization class
 * 
 * @author psm
 * 
 */
public interface ITestInit
{

	/**
	 * This method will be called when the test finishes
	 * 
	 * @param testName Test name associated to this initialization
	 * @throws BenchmarkException
	 */
	void stop (String testName) throws BenchmarkException;

	/**
	 * This method will be called when the test starts, before execute any job
	 * 
	 * @param context Spring context to be used during initialization
	 * @param testName Test name
	 * @throws BenchmarkException
	 */
	void init (ApplicationContext context, String testName) throws BenchmarkException;
}
