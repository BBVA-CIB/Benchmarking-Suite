/**
 * 
 */
package com.bbva.kltt.benchmark.exec;

/**
 * Represents a worker result
 * 
 * @author psm
 * @param <T> Restult type
 * 
 */
public class WorkerResult<T>
{
	/** Worker id */
	private Integer id;

	/** Worker instance */
	private Integer instance;

	/** Result value */
	private T result;

	/**
	 * Gets the worker identifier
	 * 
	 * @return The identifer
	 */
	public Integer getId ()
	{
		return this.id;
	}

	/**
	 * Sets the worker identifier
	 * 
	 * @param id Value to set
	 */
	public void setId (final Integer id)
	{
		this.id = id;
	}

	/**
	 * Sets the worker instance
	 * 
	 * @param instance Value to set
	 */
	public void setInstance (final Integer instance)
	{
		this.instance = instance;
	}

	/**
	 * Gets the instance id
	 * 
	 * @return The instance id
	 */
	public Integer getInstance ()
	{
		return this.instance;
	}

	/**
	 * Sets the result
	 * 
	 * @param result The result to set
	 */
	public void setResult (final T result)
	{
		this.result = result;
	}

	/**
	 * Gets the result
	 * 
	 * @return The result value
	 */
	public T getResult ()
	{
		return this.result;
	}

}
