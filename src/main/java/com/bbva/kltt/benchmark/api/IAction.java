package com.bbva.kltt.benchmark.api;

/**
 * Interface used to define a new Test class
 * 
 * @param <T> Type returned by this test
 */
public interface IAction<T>
{

	/**
	 * Contains the code with the test logic
	 * 
	 * @param testContext Test context, it includes all test needs
	 * @param jobContext Job context
	 * 
	 * @return The test result
	 */
	ActionResult<T> execute (TestContext testContext, JobContext jobContext);

}
