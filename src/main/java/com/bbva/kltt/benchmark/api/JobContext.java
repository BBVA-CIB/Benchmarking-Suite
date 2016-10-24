/**
 * 
 */
package com.bbva.kltt.benchmark.api;

import com.bbva.mon.performancemonitor.api.MeasuresCache;
import com.bbva.mon.performancemonitor.api.PerformanceMonitor;

/**
 * Represents one Job instance context
 * 
 * @author psm
 *
 */
public class JobContext
{
	/** Store the defined user measures */
	private MeasuresCache userMeasures = PerformanceMonitor.getMeasuresCache();
	
	/** Job iteration */
	private int jobIteration;

	/**
	 * Gets user measures
	 * 
	 * @return the userMeasures
	 */
	public MeasuresCache getUserMeasures ()
	{
		return userMeasures;
	}

	/**
	 * Sets user measures
	 * 
	 * @param userMeasures the userMeasures to set
	 */
	public void setUserMeasures (final MeasuresCache userMeasures)
	{
		this.userMeasures = userMeasures;
	}

	/**
	 * Gets jobIteration 
	 * 
	 * @return the jobIteration
	 */
	public int getJobIteration ()
	{
		return jobIteration;
	}

	/**
	 * Sets jobIteration 
	 * 
	 * @param jobIteration the jobIteration to set
	 */
	public void setJobIteration (final int jobIteration)
	{
		this.jobIteration = jobIteration;
	}
	
}
