/**
 * 
 */
package com.bbva.kltt.benchmark.measures.actions;

import com.bbva.kltt.benchmark.api.ActionResult;
import com.bbva.kltt.benchmark.api.IAction;
import com.bbva.kltt.benchmark.api.JobContext;
import com.bbva.kltt.benchmark.api.TestContext;

/**
 * Action to take a finish time
 * 
 * @author psm
 * 
 */
public class StartMeasure implements IAction<Long>
{
	/** Measure name. Injected */
	private String measureName;


	/**
	 * Gets the measure name
	 * 
	 * @return the measureName
	 */
	public String getMeasureName ()
	{
		return measureName;
	}

	/**
	 * Sets the measure name
	 * 
	 * @param measureName the measureName to set
	 */
	public void setMeasureName (final String measureName)
	{
		this.measureName = measureName;
	}

	@Override
	public ActionResult<Long> execute (final TestContext testContext, final JobContext jobContext)
	{
		jobContext.getUserMeasures().addStartMeasure(this.measureName, jobContext.getJobIteration(), testContext.getTimeUtil().getTime());
		return new ActionResult<Long>();
	}

}
