/**
 * 
 */
package com.bbva.kltt.benchmark.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.bbva.kltt.benchmark.model.adapters.ConditionScope;
import com.bbva.kltt.benchmark.model.adapters.DependsAdapter;

/**
 * Represents a Job
 * 
 * @author psm
 * 
 */
@XmlType(name = "job")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobData
{
	/** Job identifier */
	@XmlAttribute(required = true)
	private Integer id;

	/** Job name */
	@XmlAttribute(required = true)
	private String name;

	/** Job condition */
	@XmlAttribute
	private String conditionBean;

	/** Job condition scope. Possible values are ACTION, JOB */
	@XmlAttribute
	private ConditionScope conditionScope = ConditionScope.JOB;

	/** Action list in this job */
	@XmlElement(name = "action")
	private List<ActionData> actionsData = new ArrayList<ActionData>();

	/** Job dependencies */
	@XmlAttribute
	@XmlJavaTypeAdapter(DependsAdapter.class)
	private Map<Integer, String> depends = new HashMap<Integer, String>();

	/** Job instances */
	@XmlAttribute
	private Integer instances = 1;

	/** Time to wait before first job execution */
	@XmlAttribute
	private Long delay = 0L;

	/** Time to wait in the nexts job executions */
	@XmlAttribute
	private Long period = 0L;
	
	/** Indicates if the job is periodic or not */
	@XmlAttribute
	private boolean periodic = false;
	
	/** Indicates if the measures will be take at iteration level */
	@XmlAttribute
	private boolean measures = false;
	
	/**
	 * Gets Identifier
	 * 
	 * @return Job identifer
	 */
	public Integer getId ()
	{
		return id;
	}

	/**
	 * Sets identifier
	 * 
	 * @param id Sets the job identifier
	 */
	public void setId (final Integer id)
	{
		this.id = id;
	}

	/**
	 * Gets the job name
	 * 
	 * @return job name
	 */
	public String getName ()
	{
		return name;
	}

	/**
	 * Sets the job name
	 * 
	 * @param name Job name
	 */
	public void setName (final String name)
	{
		this.name = name;
	}

	/**
	 * Gets the action list
	 * 
	 * @return the action list
	 */
	public List<ActionData> getActionsData ()
	{
		return actionsData;
	}

	/**
	 * Sets the action list
	 * 
	 * @param actionsData Action data list to set
	 */
	public void setActionsData (final List<ActionData> actionsData)
	{
		this.actionsData = actionsData;
	}

	/**
	 * Sets the action dependencies
	 * 
	 * @param depends action dependencies
	 */
	public void setDepends (final Map<Integer, String> depends)
	{
		this.depends = depends;
	}

	/**
	 * Gets dependencies
	 * 
	 * @return Dependencies
	 */
	public Map<Integer, String> getDepends ()
	{
		return this.depends;
	}

	/**
	 * Gets the instances number
	 * 
	 * @return Instances number
	 */
	public Integer getInstances ()
	{
		return instances;
	}

	/**
	 * Sets the intances number
	 * 
	 * @param instances Instance number to set
	 */
	public void setInstances (final Integer instances)
	{
		this.instances = instances;
	}

	/**
	 * Gets the delay
	 * 
	 * @return delay for the first execution
	 */
	public Long getDelay ()
	{
		return delay;
	}

	/**
	 * Sets the delay
	 * 
	 * @param delay Delay to set
	 */
	public void setDelay (final Long delay)
	{
		this.delay = delay;
	}

	/**
	 * Gets the periodicity of the action
	 * 
	 * @return Action period
	 */
	public Long getPeriod ()
	{
		return this.period;
	}

	/**
	 * Sets the action period
	 * 
	 * @param period Period to set
	 */
	public void setPeriod (final Long period)
	{
		this.period = period;
	}

	/**
	 * Gets the condition bean name
	 * 
	 * @return Condition bean name
	 */
	public String getConditionBean ()
	{
		return conditionBean;
	}

	/**
	 * Sets the condition bean name
	 * 
	 * @param conditionBean Condition bean name
	 */
	public void setConditionBean (final String conditionBean)
	{
		this.conditionBean = conditionBean;
	}

	/**
	 * Gets the condition scope
	 * 
	 * @return the conditionScope
	 */
	public ConditionScope getConditionScope ()
	{
		return conditionScope;
	}

	/**
	 * Sets condition scope
	 * 
	 * @param conditionScope the conditionScope to set
	 */
	public void setConditionScope (final ConditionScope conditionScope)
	{
		this.conditionScope = conditionScope;
	}

	/**
	 * Gets the periodic value
	 * 
	 * @return the periodic
	 */
	public boolean isPeriodic ()
	{
		return periodic;
	}

	/**
	 * Sets the periodic value 
	 * 
	 * @param periodic the periodic to set
	 */
	public void setPeriodic (final boolean periodic)
	{
		this.periodic = periodic;
	}

	/**
	 * Gets the measure value
	 * 
	 * @return the measures
	 */
	public boolean isMeasures ()
	{
		return this.measures;
	}

	/**
	 * Sets the measure value
	 * 
	 * @param measures the measures to set
	 */
	public void setMeasures (final boolean measures)
	{
		this.measures = measures;
	}

	@Override
	public String toString ()
	{
		return "JobData [id=" + id + ", name=" + name + ", conditionBean=" + conditionBean + ", conditionScope="
				+ conditionScope + ", actionsData=" + actionsData + ", depends=" + depends + ", instances=" + instances
				+ ", delay=" + delay + ", period=" + period + ", periodic=" + periodic + ", measures=" + measures + "]";
	}

}
