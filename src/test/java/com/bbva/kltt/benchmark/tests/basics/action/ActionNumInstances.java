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
public class ActionNumInstances implements IAction<Boolean>
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ActionTest.class);

	@Override
	public ActionResult<Boolean> execute (TestContext testContext, JobContext jobContext)
	{
		LOGGER.info("ActionTest invoked for test ({})", testContext.getTestName());
		
//		InitTest initTest = ((InitTest) testContext.getInitializables().get("InitJUnit"));
		CacheActions.getInstance().addInstance(testContext.getTestName());
		ActionResult<Boolean> result = new ActionResult<Boolean>();
		LOGGER.info("Executing ActionTest for test ({})", testContext.getTestName());
		return result;
	}

}
