package com.bbva.kltt.benchmark.util;

/**
 * Return the time in Millisenconds
 * 
 * @author psm
 * 
 */
public class TimeMilliseconds implements ITimeUtil
{
	/**
	 * Gets the current time in MILLIS
	 * 
	 * 
	 * @return Current time
	 */
	public long getTime ()
	{
		return System.currentTimeMillis();
	}
}
