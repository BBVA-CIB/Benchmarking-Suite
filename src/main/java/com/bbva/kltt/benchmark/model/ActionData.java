/**
 * 
 */
package com.bbva.kltt.benchmark.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Represents an action
 * 
 * @author psm
 * 
 */
@XmlType(name="action")
@XmlAccessorType(XmlAccessType.FIELD)
public class ActionData
{
	/** Action id */
	@XmlAttribute
	private Integer id;

	/** Action name */
	@XmlAttribute
	private String name;

	/** Bean name that contains the action instance */
	@XmlAttribute
	private String beanName;

	/**
	 * Gets the action id
	 * 
	 * @return The action id
	 */
	public Integer getId ()
	{
		return id;
	}

	/**
	 * Sets the action id
	 * 
	 * @param id Action id to set
	 * 
	 */
	public void setId (final Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the action name
	 * 
	 * @return the action name
	 */
	public String getName ()
	{
		return name;
	}

	/**
	 * Sets the name
	 * 
	 * @param name the name to set
	 */
	public void setName (final String name)
	{
		this.name = name;
	}

	/**
	 * Gets the bean name that contains the action to execute
	 * 
	 * @return the bean name
	 */
	public String getBeanName ()
	{
		return beanName;
	}

	/**
	 * Gets the bean name
	 * 
	 * @param beanName The bean name to set
	 */
	public void setBeanName (final String beanName)
	{
		this.beanName = beanName;
	}

	@Override
	public String toString ()
	{
		return "\nAction [id=" + id + ", name=" + name + ", beanName=" + beanName + "]";
	}
}
