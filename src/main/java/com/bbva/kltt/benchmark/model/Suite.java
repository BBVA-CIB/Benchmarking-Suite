/**
 * 
 */
package com.bbva.kltt.benchmark.model;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents the root element
 * 
 * @author psm
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Suite
{
	/** Test list */
	@XmlElement(name = "test")
	private List<TestData> testsData;

	/** Measures time unit (MILLIS/NANO) */
	@XmlAttribute
	private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

	/** Indicates if the statistics should be exported to JSON format */
	@XmlAttribute
	private boolean exportJSON = true;

	/** Indicates if the statistics should be exported to CSV format */
	@XmlAttribute
	private boolean exportCSV = true;

	/**
	 * Sets the test list
	 * 
	 * @param testsData Test Data list to set
	 */
	public void setTestsData (final List<TestData> testsData)
	{
		this.testsData = testsData;
	}

	/**
	 * Gets the test list
	 * 
	 * @return The test list
	 */
	public List<TestData> getTestsData ()
	{
		return this.testsData;
	}

	/**
	 * Gets the time unit
	 * 
	 * @return the unit
	 */
	public TimeUnit getTimeUnit ()
	{
		return this.timeUnit;
	}

	/**
	 * Sets the time unit
	 * 
	 * @param timeUnit the unit to set
	 */
	public void setTimeUnit (final TimeUnit timeUnit)
	{
		this.timeUnit = timeUnit;
	}

	/**
	 * Gets exportJSON value
	 * 
	 * @return the exportJSON
	 */
	public boolean isExportJSON ()
	{
		return exportJSON;
	}

	/**
	 * Gets exportCSV value
	 * 
	 * @return the exportCSV
	 */
	public boolean isExportCSV ()
	{
		return exportCSV;
	}

	/**
	 * Sets exportJSON
	 * 
	 * @param exportJSON the exportJSON to set
	 */
	public void setExportJSON (final boolean exportJSON)
	{
		this.exportJSON = exportJSON;
	}

	/**
	 * Sets exportCSV
	 * 
	 * @param exportCSV the exportCSV to set
	 */
	public void setExportCSV (final boolean exportCSV)
	{
		this.exportCSV = exportCSV;
	}

	@Override
	public String toString ()
	{
		return "Suite [tests=" + testsData + "]";
	}
}
