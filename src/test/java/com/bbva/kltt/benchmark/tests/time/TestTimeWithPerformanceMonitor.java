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
public class TestTimeWithPerformanceMonitor
{
	private HashMap<Integer, Integer> map;
	private HashMap<String, String> mapString;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass () throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass () throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp () throws Exception
	{
		map = new HashMap<Integer, Integer>();
		mapString = new HashMap<String, String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown () throws Exception
	{
	}

	
	@Test
	public final void testAddToMapWithoutBenchmarkStatistics ()
	{
//		int key = 0;
//		// WARM
//		for (int i = 0; i < 10000; i++)
//		{
//			for (int j = 0; j < 100; j++)
//			{
//				map.put(key++, j);
//			}
//		}
//		
//		List<Long> measures = new ArrayList<Long>();
//		// Real test
//		key = 0;
//		map.clear();
//		for (int i = 0; i < 10000; i++)
//		{
//			long start = System.nanoTime();
//			for (int j = 0; j < 100; j++)
//			{
//				map.put(key++, j);
//			}
//			measures.add(System.nanoTime() - start);
//		}
//		MeasuresCache mc = PerformanceMonitor.getMeasuresCache();
//		mc.addMeasure("IntegerMap", measures);
//		
//		mc.generateStatistics();
//		mc.exportCSV("testAddToMapWithoutBenchmarkStatistics");
	}	
	
	@Test
	public final void testAddToMapStringWithoutBenchmarkStatistics ()
	{
//		int key = 0;
//		mapString.clear();
//		for (int i = 0; i < 1000; i++)
//		{
//			for (int j = 0; j < 100; j++)
//			{
//				mapString.put("key" + key++, "value" + j);
//			}
//		}		
//		List<Long> measures = new ArrayList<Long>();
//		// Real test
//		key = 0;
//		mapString.clear();
//		for (int i = 0; i < 1000; i++)
//		{
//			long start = System.nanoTime();
//			for (int j = 0; j < 100; j++)
//			{
//				mapString.put("key" + key++, "value" + j);
//			}
//			measures.add(System.nanoTime() - start);
//		}
//		MeasuresCache mc = PerformanceMonitor.getMeasuresCache();
//		mc.addMeasure("AddStringMap", measures);
//		
//		mc.generateStatistics();
//		mc.exportCSV("testAddToMapStringWithoutBenchmarkStatistics");
	}	
	
	@Test
	public final void testReadFromMapWithoutBenchmarkStatistics()
	{
//		Map<String, String> mapString = new HashMap<String, String>();
//		for (int j = 0; j < 10000; j++)
//		{
//			mapString.put("key" + j, "value" + j);
//		}
//		
//		// WARM
//		for (int j = 0; j < 10000; j++)
//		{
//			String value = mapString.get("key" + j);
//			if( value != null )
//			{
//				mapString.put("key" + j, "new Value");
//			}
//			else
//			{
//				mapString.put("key" + j, "new insert");
//			}
//		}		
//		List<Long> measures = new ArrayList<Long>();
//		// Real test
//	
//		for (int j = 0; j < 10000; j++)
//		{
//			long start = System.nanoTime();
//			String value = mapString.get("key" + j);
//			if( value != null )
//			{
//				mapString.put("key" + j, "new Value");
//			}
//			else
//			{
//				mapString.put("key" + j, "new insert");
//			}
//			measures.add(System.nanoTime() - start);
//		}
//		MeasuresCache mc = PerformanceMonitor.getMeasuresCache();
//		mc.addMeasure("ReadStringMap", measures);
//		
//		mc.generateStatistics();
//		mc.exportCSV("testReadFromMapWithoutBenchmarkStatistics");
	}	

}
