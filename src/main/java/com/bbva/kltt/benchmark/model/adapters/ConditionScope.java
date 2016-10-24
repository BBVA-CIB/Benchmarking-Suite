/**
 * 
 */
package com.bbva.kltt.benchmark.model.adapters;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Enum that represents the condition scope type:
 * 
 * ACTION / JOB
 * 
 * @author psm
 * 
 */
@XmlType(name = "conditionScope")
@XmlEnum(String.class)
public enum ConditionScope
{
	/** Action scope */
	ACTION, 
	/** Job scope */
	JOB
}
