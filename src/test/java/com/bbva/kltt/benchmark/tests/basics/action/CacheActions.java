/**
 * 
 */
package com.bbva.kltt.benchmark.tests.basics.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author psm
 * 
 */
public class CacheActions
{
	private static CacheActions instance = new CacheActions();
	private List<String> ejecutions = new ArrayList<String>();
	private Map<String, Integer> ejecutionsPerTest = new HashMap<String, Integer>();
	private Set<String> initPerTest = new HashSet<String>();

	/**
	 * @return
	 */
	public static CacheActions getInstance ()
	{
		return instance;
	}

	/**
	 * @param testName
	 */
	public void addEjecution (String testName)
	{
		ejecutions.add(testName);
	}

	public boolean compareEjecutions (List<String> ejecutionsExpected)
	{
		return this.ejecutions.equals(ejecutionsExpected);
	}

	/**
	 * @param testName
	 */
	public synchronized void addInstance (String testName)
	{
		int currentNumberOfInstances = 0;
		if( ejecutionsPerTest.containsKey(testName) )
		{
			currentNumberOfInstances = ejecutionsPerTest.get(testName);
		}
		ejecutionsPerTest.put(testName, ++currentNumberOfInstances);
	}
	
	public boolean compareInstances(String testName, int numInstances)
	{
		return ejecutionsPerTest.get(testName).equals(numInstances);
	}

	/**
	 * @param string
	 * @return
	 */
	public boolean getInit (String testName)
	{
		return initPerTest.contains(testName);
	}
	
	public void addInit(String testName)
	{
		initPerTest.add(testName);
	}
}
