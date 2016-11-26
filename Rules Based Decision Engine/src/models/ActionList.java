package models;

import services.ActionCollectionService;

import java.util.HashMap;

import exceptions.ActionException;
import models.Action;

/**
 * This class contains a list of actions associated with a rule.
 * 
 * @author Trae X. Lewis
 * @version 1.00
 */
public class ActionList 
{
	
	ActionCollectionService act_svc = ActionCollectionService.getInstance();
	//TODO: Create linked hashMap to maintain order.
	HashMap<String, Action> actionMap;
	Action action;
	
	
	public ActionList()
	{
		this.actionMap = new HashMap<String, Action>();
	}
	
	
	/**
	 * Inserts <key,value> pair into the ActionList map.
	 * @param name key
	 * @param action value
	 */
	public void insertAction(String name , Action action)
	{	
		if (keyCheck(name, action))
		{
			actionMap.put(name, action);
		}
		//TODO: add functionality to ask the user to update individual fields based on input. 
	}
	
	/**
	 * Removes action from the map based on key.
	 * @param name
	 */
	public void removeAction(String name)
	{
		name.toUpperCase().trim();
		actionMap.remove(name);
	}
	
	/**
	 * Checks the <key,value> pair to see if it already exists inside ActionCollecionSerivce, either
	 * in its entirety or just the key or value. 
	 * - Neither key or value exists as a pair or separately a map entry is added to the ActionCollectionService.
	 * @param name key
	 * @param action value
	 * @return returns true if pair exists either in its entirety as a single Map entry or not at all, false otherwise.
	 */
	private boolean keyCheck(String name, Action action)
	{
		boolean result = false;
		name = name.toUpperCase().trim();
		
		if(act_svc.containsKey(name))
		{
			this.action = act_svc.get(name);
			if(this.action.equals(action))
			{
				result = true;
				// <key, value> pair exists in ActionCollectionService.
			}
			else
			{
				// <key already exists but action is different.
				//TODO: should user update existing <key,value> pair or should user enter a new key for the value?
			}
		}
		else if (act_svc.containsValue(this.action))
		{
			//TODO: Notify the user that action already exists in a different <key,value> pair. 
			//TODO: Prompt the user for a new Key or ask the user if they want to use existing <key,value> pair matching key.
		}
		else
		{
			result = true;
			act_svc.put(name, action);
		}
		
		return result;
	}
	
	public String toString()
	{
		String temp = "";
		for (Action value : actionMap.values())
		{
			temp += value + "\n";
		}
		return temp;
	}

}
