/**
 * 
 */
package com.bbva.kltt.benchmark;


/**
 * Stores the version 
 * 
 * @author psm
 *
 */
public final class Version
{
	/** Project version */
	private static final String VERSION_NUMBER = "2.0.0";
	
	/**
	 * Constructor
	 * 
	 */
	private Version(){}
	
	/**
	 * Gets the version
	 *  
	 * @return version
	 */
	public static String getVersionNumber()
	{
		return Version.VERSION_NUMBER;
	}
}
