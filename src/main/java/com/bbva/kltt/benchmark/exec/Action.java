/**
 * 
 */
package com.bbva.kltt.benchmark.exec;

import com.bbva.kltt.benchmark.api.ICondition;
import com.bbva.kltt.benchmark.model.ActionData;

/**
 * Represents an action in the suite
 * 
 * @author psm
 *
 */
public class Action
{

	/** Action data associated to this action*/
	private ActionData actionData;
	
	/** Action condition */
	private ICondition<?> condition;

	/**
	 * Constructor
	 * 
	 * @param actionData Action data associated
	 */
	public Action(final ActionData actionData)
	{
		this.actionData = actionData;
	}

	/**
	 * Sets action condition
	 * 
	 * @param condition
	 */
	public void setCondition (final ICondition<?> condition)
	{
		this.condition = condition;
	}

	/**
	 * Gets condition 
	 * 
	 * @return the condition
	 */
	public ICondition<?> getCondition()
	{
		return this.condition;
	}
	/**
	 * Sets action data
	 * 
	 * @return the actionData
	 */
	public ActionData getActionData ()
	{
		return actionData;
	}

	/**
	 * Gets action data
	 * 
	 * @param actionData the actionData to set
	 */
	public void setActionData (final ActionData actionData)
	{
		this.actionData = actionData;
	}

}
