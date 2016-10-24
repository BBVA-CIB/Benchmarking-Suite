/**
 * 
 */
package com.bbva.kltt.benchmark.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.bbva.kltt.benchmark.model.adapters.DependsAdapter;

/**
 * This class represents a defined test in the configuration file
 * 
 * @author psm
 * 
 */
@XmlType(name = "test")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestData
{
	/** Test id */
	@XmlAttribute
	private Integer id;

	/** Test name */
	@XmlAttribute
	private String name;

	/** Test dependencies */
	@XmlAttribute
	@XmlJavaTypeAdapter(DependsAdapter.class)
	private Map<Integer, String> depends = new HashMap<Integer, String>();

	/** Job list */
	@XmlElement(name="job")
	private List<JobData> jobsData;

	/** Initialization */
	private InitData initialization;

	/**
	 * Sets test id
	 * 
	 * @param id The test id
	 */
	public void setId (final Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the test name
	 * 
	 * @return The test name
	 */
	public String getName ()
	{
		return name;
	}

	/**
	 * Sets the name
	 * 
	 * @param name The name to set
	 */
	public void setName (final String name)
	{
		this.name = name;
	}

	/**
	 * Sets the test id
	 * 
	 * @return The id to set
	 */
	public Integer getId ()
	{
		return id;
	}

	/**
	 * Gets the job list
	 * 
	 * @return Job list
	 */
	public List<JobData> getJobsData ()
	{
		return this.jobsData;
	}

	/**
	 * Sets the job list
	 * 
	 * @param jobsData the job data list to set
	 */
	public void setJobsData (final List<JobData> jobsData)
	{
		this.jobsData = jobsData;
	}

	/**
	 * Sets job dependencies
	 * 
	 * @param depends Jobs dependencies to set
	 */
	public void setDepends (final Map<Integer, String> depends)
	{
		this.depends = depends;
	}

	/**
	 * Gets dependency list
	 * 
	 * @return the dependencies
	 */
	public Map<Integer, String> getDepends ()
	{
		return this.depends;
	}


	/**
	 * Gets initialization
	 * 
	 * @return the initialization
	 */
	public InitData getInitialization ()
	{
		return initialization;
	}

	/**
	 * Sets initialization
	 * 
	 * @param initialization the initialization to set
	 */
	public void setInitialization (final InitData initialization)
	{
		this.initialization = initialization;
	}

	@Override
	public String toString ()
	{
		return "TestData [id=" + id + ", name=" + name + ", depends=" + depends + ", jobsData=" + jobsData
				+ ", initialization=" + initialization + "]";
	}

}
