/**
 * 
 */
package com.bbva.kltt.benchmark.tests.basics.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.benchmark.api.ActionResult;
import com.bbva.kltt.benchmark.api.IAction;
import com.bbva.kltt.benchmark.api.JobContext;
import com.bbva.kltt.benchmark.api.TestContext;

/**
 * @author psm
 *
 */
public class ActionTest implements IAction<Integer>
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActionTest.class);

	@Override
	public ActionResult<Integer> execute (TestContext testContext, JobContext jobContext)
	{
		LOGGER.info("ActionTest invoked for test ({})", testContext.getTestName());
		
//		InitTest initTest = ((InitTest) testContext.getInitializables().get("InitJUnit"));
		
		// Action example
		int j = 1;
		for( int i = 1; i < 10; i ++)
		{
			j = j * i;
		}
		
		ActionResult<Integer> result = new ActionResult<Integer>();
		result.setResult(j);
		
		LOGGER.info("Executing ActionTest for test ({})", testContext.getTestName());
		return result;
	}
}
