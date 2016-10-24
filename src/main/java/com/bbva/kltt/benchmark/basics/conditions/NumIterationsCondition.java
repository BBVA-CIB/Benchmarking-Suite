/**
 * 
 */
package com.bbva.kltt.benchmark.basics.conditions;

import com.bbva.kltt.benchmark.api.ICondition;

/**
 * Condition based on a the number of iterations
 * 
 * @author psm
 *
 */
public class NumIterationsCondition implements ICondition<Object>
{

//	/** LOGGER */
//	private static final Logger LOGGER = LoggerFactory.getLogger(NumIterationsCondition.class);
	
	/** Number of iterations. Injected */
	private int numIterations;
	
	/** Current iterations number */
	private volatile int currentIterations = 0;

	@Override
	public boolean check (final Object value)
	{
//		if (LOGGER.isDebugEnabled())
//		{
//			LOGGER.debug("Checking iterations max ({}), current ({})", numIterations, currentIterations + 1);
//		}
		
		return ++currentIterations <= numIterations;
	}
	/**
	 * Gets the number of iterations
	 * 
	 * @return the numIterations
	 */
	public int getNumIterations ()
	{
		return numIterations;
	}
	/**
	 * Sets the number of iterations
	 * 
	 * @param numIterations the numIterations to set
	 */
	public void setNumIterations (final int numIterations)
	{
		this.numIterations = numIterations;
	}
}
