/**
 * 
 */
package com.bbva.kltt.benchmark.exec;

/**
 * Listener class to check if a Work finished its execution
 * 
 * @author psm
 * 
 */
public interface FinishListener
{
	/**
	 * This method will be call each time a Work finishes
	 * 
	 * @param wResult Work result
	 */
	void onFinished (WorkerResult<?> wResult);
}
