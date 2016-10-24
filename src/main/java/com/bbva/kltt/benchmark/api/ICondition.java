/**
 * 
 */
package com.bbva.kltt.benchmark.api;

/**
 * Interface that must implement any Job condition
 * 
 * @author psm
 * @param <T> Condition type
 * 
 */
public interface ICondition<T>
{
	/**
	 * Checks if the condition matches
	 * 
	 * @param value value used to check the condition
	 * @return True if the condition matches, false otherwise
	 */
	boolean check (T value);
}
