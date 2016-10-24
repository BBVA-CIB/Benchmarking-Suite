/**
 * 
 */
package com.bbva.kltt.benchmark.util;

/**
 * Return the time in Nanoseconds
 * 
 * @author psm
 * 
 */
public class TimeNanoseconds implements ITimeUtil
{
	/**
	 * Gets the current time in NANOSECONDS
	 * 
	 * @return Current time
	 */
	public long getTime ()
	{
		return System.nanoTime();
	}
}
