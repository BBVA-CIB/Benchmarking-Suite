/**
 * 
 */
package com.bbva.kltt.benchmark.basics.conditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.kltt.benchmark.api.ICondition;

/**
 * Condition based on a expiration time. It will check how many time has been executing the action
 * 
 * @author psm
 * 
 */
public class ExpirationTimeCondition implements ICondition<Object>
{
	/** LOGGER */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExpirationTimeCondition.class);
	
	/** Expiration time ( milliseconds ). Injected */
	private Long expirationTime;
	
	/** First execution time */
	private volatile Long firstTime = null;

	@Override
	public boolean check (final Object value)
	{
		boolean res = true;
		synchronized (this)
		{
			if (firstTime == null)
			{
				firstTime = System.currentTimeMillis();
				LOGGER.debug("Setting first time ({})", firstTime);
			}
		}
		if ((firstTime + expirationTime) < System.currentTimeMillis())
		{
			LOGGER.debug("Time expired (fist + expiration = ({}), current = ({})) ", firstTime + expirationTime, System.currentTimeMillis());
			res = false;
		}

		return res;
	}

	/**
	 * Gets the expiration time
	 * 
	 * @return Expiration time
	 */
	public Long getExpirationTime ()
	{
		return expirationTime;
	}

	/**
	 * Sets the expiration time
	 * 
	 * @param expirationTime Expiration time to set
	 */
	public void setExpirationTime (final Long expirationTime)
	{
		this.expirationTime = expirationTime;
	}

}
