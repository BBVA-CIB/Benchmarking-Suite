/**
 * 
 */
package com.bbva.kltt.benchmark.api;

/**
 * Represents the Result for a test
 * 
 * @author psm
 * @param <T> Result type
 * 
 */
public class ActionResult<T>
{
	/** Contains the result */
	private T result;

	/**
	 * Sets the result value
	 * 
	 * @param res The result value
	 */
	public void setResult (final T res)
	{
		this.result = res;
	}

	/**
	 * Gets the result value
	 * 
	 * @return The result value
	 */
	public T getResult ()
	{
		return this.result;
	}
}
