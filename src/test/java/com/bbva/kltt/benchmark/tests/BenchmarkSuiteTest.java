package com.bbva.kltt.benchmark.tests;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.bbva.kltt.benchmark.Benchmark;
import com.bbva.kltt.benchmark.exception.BenchmarkException;
import com.bbva.kltt.benchmark.model.ActionData;
import com.bbva.kltt.benchmark.tests.basics.action.CacheActions;
import com.bbva.mon.performancemonitor.exception.PerformanceMonitorException;

/**
 * 
 */

/**
 * @author psm
 *
 */
public class BenchmarkSuiteTest
{

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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown () throws Exception
	{
	}

	@Test
	public final void exec()
	{
		try
		{
			Benchmark.main(new String[]{"C:/comunytek/BenchmarkSuite/src/test/resources/benchTesting.xml"});
		}
		catch (BenchmarkException e)
		{
			e.printStackTrace();
			assert(false);
		}
		catch (PerformanceMonitorException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(true);
	}
	
	@Test
	@Ignore
	public final void actionData()
	{
		ActionData actData = new ActionData();
		actData.setBeanName("testing");
		actData.setId(2);
		actData.setName("testing");
		assertTrue(true);
	}
	
	@Test
	@Ignore
	public final void BenchException()
	{
		new BenchmarkException("Error in benchmark");
		new BenchmarkException("Exception", new Throwable());
		assertTrue(true);
	}
	
	@Test
	@Ignore
	public final void dependenciesTests1()
	{
		try
		{
			Benchmark.main(new String[]{"C:/comunytek/BenchmarkSuite/src/test/resources/DependencyTesting1.xml"});
			List<String> list = new ArrayList<String>();
			list.add("test1");
			list.add("test2");
			list.add("test3");
			assertTrue(CacheActions.getInstance().compareEjecutions(list ));
		}
		catch (BenchmarkException e)
		{
			e.printStackTrace();
		}
		catch (PerformanceMonitorException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
//	public final void testsManagerWithMockito()
//	{
//		com.bbva.kytg.benchmark.exec.Test mockedTest = mock(com.bbva.kytg.benchmark.exec.Test.class);
//		com.bbva.kytg.benchmark.exec.TestsManager mockedTestsManager = mock(com.bbva.kytg.benchmark.exec.TestsManager.class);
//		when(mockedTestsManager.execute()).
//		TestsManager manager = new TestsManager(tests, TimeUnit.);
//	}
	@Test
	@Ignore
	public final void testJobInstances() throws BenchmarkException, PerformanceMonitorException
	{
		Benchmark.main(new String[]{"C:/comunytek/BenchmarkSuite/src/test/resources/JobInstancesTesting1.xml"});

	}
	
	@Test
	@Ignore
	public final void checkInit() throws BenchmarkException, PerformanceMonitorException
	{
		Benchmark.main(new String[]{"C:/comunytek/BenchmarkSuite/src/test/resources/JobInstancesTesting1.xml"});
		assertTrue(CacheActions.getInstance().getInit("test1"));
		
	}	
}
