/**
 * 
 */
package com.bbva.kltt.benchmark.tests.time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bbva.mon.performancemonitor.api.MeasuresCache;
import com.bbva.mon.performancemonitor.api.PerformanceMonitor;

/**
 * @author psm
 * 
 */
public class TestTime
{
//	private HashMap<Integer, Integer> map;
//	private HashMap<String, String> mapString;
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@BeforeClass
//	public static void setUpBeforeClass () throws Exception
//	{
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@AfterClass
//	public static void tearDownAfterClass () throws Exception
//	{
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@Before
//	public void setUp () throws Exception
//	{
//		map = new HashMap<Integer, Integer>();
//		mapString = new HashMap<String, String>();
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@After
//	public void tearDown () throws Exception
//	{
//	}
//
//	@Test
//	public final void testAddToMapWithoutBenchmark ()
//	{
//		//WARM
//		int key = 0;
//		for (int i = 0; i < 10000; i++)
//		{
//			for (int j = 0; j < 100; j++)
//			{
//				map.put(key++, j);
//			}
//		}
//		
//		map.clear();
//		// Real test
//		key = 0;
//		map.clear();
//		List<Long> values = new ArrayList<Long>();
//		for (int i = 0; i < 10000; i++)
//		{
//			long start = System.nanoTime();
//			for (int j = 0; j < 100; j++)
//			{
//				map.put(key++, j);
//			}
//			values.add(System.nanoTime()-start);
//		}
//		
//		long sum = 0;
//		for( int i = 0; i < values.size(); i ++)
//		{
//			sum += values.get(i);
//		}
//		System.out.println("BASICtestAddToMapWithoutBenchmark: " + sum/values.size());
//		MeasuresCache cache = PerformanceMonitor.getMeasuresCache();
//		cache.addMeasure("testAddToMapWithoutBenchmark", values);
//		cache.exportCSV("BASICtestAddToMapWithoutBenchmark");
//	}
//	
//
//	@Test
//	public final void testAddToMapStringWithoutBenchmark ()
//	{
//		int key = 0;
//		mapString.clear();
//		//WARM
//		for (int i = 0; i < 10000; i++)
//		{
//			for (int j = 0; j < 100; j++)
//			{
//				mapString.put("key" + key++, "value" + j);
//			}
//		}
//		// Real test
//		key = 0;
//		List<Long> values = new ArrayList<Long>();
//		mapString.clear();
//		
//		for (int i = 0; i < 10000; i++)
//		{
//			long start = System.nanoTime();
//			for (int j = 0; j < 100; j++)
//			{
//				mapString.put("key" + key++, "value" + j);
//			}
//			values.add(System.nanoTime()-start);
//		}
//		long sum = 0;
//		for( int i = 0; i < values.size(); i ++)
//		{
//			sum += values.get(i);
//		}
//		System.out.println("BASICtestAddToMapStringWithoutBenchmark: " + sum/values.size());		
//		MeasuresCache cache = PerformanceMonitor.getMeasuresCache();
//		cache.addMeasure("testAddToMapStringWithoutBenchmark", values);
//		cache.exportCSV("BASICtestAddToMapStringWithoutBenchmark");
//	}
//	
//	
//	@Test
//	public final void testReadFromMapWithoutBenchmark()
//	{
//		Map<String, String> map = new HashMap<String, String>();
//		for (int j = 0; j < 10000; j++)
//		{
//			map.put("key" + j, "value" + j);
//		}
//		
//		// WARM
//		for (int j = 0; j < 10000; j++)
//		{
//			String value = map.get("key" + j);
//			if( value != null )
//			{
//				map.put("key" + j, "new Value");
//			}
//			else
//			{
//				map.put("key" + j, "new insert");
//			}
//		}		
//		List<Long> values = new ArrayList<Long>();
//		
//		// Real test
//		
//		for (int j = 0; j < 10000; j++)
//		{
//			long start = System.nanoTime();
//			String value = map.get("key" + j);
//			if( value != null )
//			{
//				map.put("key" + j, "new Value");
//			}
//			else
//			{
//				map.put("key" + j, "new insert");
//			}
//			values.add(System.nanoTime()-start);
//		}
//		
//		long sum = 0;
//		for( int i = 0; i < values.size(); i ++)
//		{
//			sum += values.get(i);
//		}
//		System.out.println("BASICtestReadFromMapWithoutBenchmark: " + sum/values.size());
//		
//		MeasuresCache cache = PerformanceMonitor.getMeasuresCache();
//		cache.addMeasure("testReadFromMapWithoutBenchmark", values);
//		cache.exportCSV("BASICtestReadFromMapWithoutBenchmark");
//	}
	

}
