/**
 * 
 */
package com.bbva.kltt.benchmark.exec;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class that encapsulates the dependency code. It stores the finished
 * works and compares them with the declared dependencies.
 * 
 * @author psm
 * 
 */
public abstract class AbstractDependantWorker extends AbstractWorker implements FinishListener
{
	/**
	 * Finished works. The key indicates the work id and the WorkerResult the
	 * retuned value
	 */
	private final Map<Integer, WorkerResult<?>> finished = new HashMap<Integer, WorkerResult<?>>();

	/**
	 * Gets the dependencies for this specific work
	 * 
	 * @return A Map with the dependencies ( the key is the work id and the
	 *         value is the work result )
	 */
	public abstract Map<Integer, String> getDepends ();

	/**
	 * Checks if all work dependencies for this job already finished
	 * 
	 * @return True if currently the work does not have dependencies, false
	 *         otherwise
	 */
	public boolean isIndependant ()
	{
		return this.finished.keySet().containsAll(this.getDepends().keySet());
	}

	@Override
	public void onFinished (final WorkerResult<?> wResult)
	{
		this.finished.put(wResult.getId(), wResult);
	}

}
