/**
 * 
 */
package com.bbva.kltt.benchmark.exception;

/**
 * BenchmarkException class used to thrown exception in this library
 * 
 * @author psm
 * 
 */
public class BenchmarkException extends Exception 
{
	/** Serial id */
	private static final long serialVersionUID = -7184560494927608743L;

	/**
	 * Constructor
	 * 
	 * @param msg Exception message
	 */
	public BenchmarkException(final String msg)
	{
		super(msg);
	}

	/**
	 * Constructor
	 * 
	 * @param msg Exception message
	 * @param cause Original exception
	 */
	public BenchmarkException(final String msg, final Throwable cause)
	{
		super(msg, cause);
	}
}
