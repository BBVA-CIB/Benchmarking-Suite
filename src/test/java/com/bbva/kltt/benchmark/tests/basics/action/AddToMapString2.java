/**
 * 
 */
package com.bbva.kltt.benchmark.tests.basics.action;

import java.util.HashMap;
import java.util.Map;

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
public class AddToMapString2 implements IAction<Integer>
{
	private static final Logger LOGGER = LoggerFactory.getLogger(AddToMapString2.class);

	private static Map<String, String> map = new HashMap<String, String>();
	private static int k = 0;

	@Override
	public ActionResult<Integer> execute (TestContext testContext, JobContext jobContext)
	{
//		LOGGER.info("ActionTest invoked for test ({})", testContext.getTestName());

//		InitTest initTest = ((InitTest) testContext.getInitializables().get("InitJUnit"));

		for (int i = 1; i < 100; i++)
		{
			map.put("key" + k++, "value" + i);
		}

		ActionResult<Integer> result = new ActionResult<Integer>();

//		LOGGER.info("Executing ActionTest for test ({})", testContext.getTestName());
		return result;
	}

}
