/**
 * 
 */
package com.bbva.kltt.benchmark.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Represents the test initialization
 * 
 * @author psm
 * 
 */
@XmlType(name="initialization")
@XmlAccessorType(XmlAccessType.NONE)
public class InitData
{
	/** Spring file used */
	@XmlAttribute
	private String file;

	/** Initialization bean names */
	@XmlElement(name = "initBean")
	private List<String> initBeanNames;

	/**
	 * Gets the spring file name
	 * 
	 * @return the file
	 */
	public String getFile ()
	{
		return this.file;
	}

	/**
	 * Set the spring file name
	 * 
	 * @param file the file to set
	 */
	public void setFile (final String file)
	{
		this.file = file;
	}

	/**
	 * Gets the initialization bean names
	 * 
	 * @return the list of initialization bean names
	 */
	public List<String> getBeanNames ()
	{
		return initBeanNames;
	}

	/**
	 * Sets the bean names
	 * 
	 * @param initBeans Bean names list to set
	 */
	public void setBeanNames (final List<String> initBeans)
	{
		this.initBeanNames = initBeans;
	}

}
