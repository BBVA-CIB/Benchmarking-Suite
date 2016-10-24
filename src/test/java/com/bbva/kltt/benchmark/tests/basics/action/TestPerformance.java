/**
 * 
 */
package com.bbva.kltt.benchmark.tests.basics.action;

import com.bbva.kltt.benchmark.api.ActionResult;
import com.bbva.kltt.benchmark.api.IAction;
import com.bbva.kltt.benchmark.api.JobContext;
import com.bbva.kltt.benchmark.api.TestContext;

/**
 * @author psm
 *
 */
public class TestPerformance implements IAction<Boolean>
{
	@Override
	public ActionResult<Boolean> execute (TestContext testContext, JobContext jobContext)
	{
		
		long x = 1;
		long y = 1;
		for( int i = 0; i < 200000; i ++)
		{
			x = x + y;
			y = y * 2 + x + 1;
			x = 4 * x / y + 3;
			x = 2 * x + y / x;
			y = 4 + x;
		}
		ActionResult<Boolean> res = new ActionResult<Boolean>();
		res.setResult(y<x);
		return res;
	}
}

