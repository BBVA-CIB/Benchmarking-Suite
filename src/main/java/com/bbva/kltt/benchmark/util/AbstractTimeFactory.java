/**
 * 
 */
package com.bbva.kltt.benchmark.util;

import java.util.concurrent.TimeUnit;

/**
 * Factory to build the proper TimeUtil to use
 * 
 * @author psm
 *
 */
 public abstract class AbstractTimeFactory
{
	 /**
	  * Gets the time util to use
	 * @param unit Time unit
	 * @return Time util to use
	 */
	public static ITimeUtil getTimeUtil(final TimeUnit unit)
	 {
		ITimeUtil result = null;
		 switch( unit )
		 {
			 case MILLISECONDS: 
				 result = new TimeMilliseconds();
				 break;
			 case NANOSECONDS: 
				 result = new TimeNanoseconds();
				 break;
			 default: 
				 result = new TimeMilliseconds();
		 }
		 return result;
	 }
}
