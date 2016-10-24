/**
 * 
 */
package com.bbva.kltt.benchmark.model.adapters;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter to translate dependencies into a Map The Map contains the test/job id
 * and a String representing a returned value. If the String is null, then only
 * the test/job will be checked
 * 
 * @author psm
 * 
 */
public class DependsAdapter extends XmlAdapter<String, Map<Integer, String>>
{
	/** Value separator */
	private static final String SEPARATOR = ",";
	/** Dependency separator */
	private static final String SEMICOLON = ":";

	@Override
	public Map<Integer, String> unmarshal (final String dependencyList) throws Exception
	{
		Map<Integer, String> result = new HashMap<Integer, String>();
		if (dependencyList == null || dependencyList.length() == 0)
		{
			return result;
		}
		String[] dependencies = dependencyList.split(SEPARATOR);
		for (String current : dependencies)
		{
			String[] valuePair = current.split(SEMICOLON);
			result.put(Integer.parseInt(valuePair[0]), valuePair.length == 2 ? valuePair[1] : null);
		}
		return result;
	}

	@Override
	public String marshal (final Map<Integer, String> dependencies) throws Exception
	{
		StringBuffer result = new StringBuffer();
		for ( Entry<Integer, String> entry: dependencies.entrySet())
		{
			result.append(entry.getKey());
			result.append(entry.getValue() == null ? SEPARATOR : SEMICOLON + entry.getValue() + SEPARATOR);
			
		}
		// Eliminate last separator
		result.setLength(result.length() - 1);
		return result.toString();
	}
}
