package com.bbva.kltt.benchmark.exec;

import java.util.concurrent.Callable;

/**
 * Abstract class that represent an invoker object. It could be a Test or Job
 * 
 * @author psm
 * 
 */
public abstract class AbstractWorker implements Callable<WorkerResult<?>>
{
	/** Indicates if this worker was already invoked */
	private boolean invoked = false;

	/**
	 * Indicates if this worker was already invoked
	 * 
	 * @return boolean indicating if this worker was already invoked
	 */
	public boolean isInvoked ()
	{
		return this.invoked;
	}

	/**
	 * Sets the invoked value
	 * 
	 * @param invoked Value to set
	 * */
	public void setInvoked (final boolean invoked)
	{
		this.invoked = invoked;
	}

}
