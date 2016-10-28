package services;
import models.Action;
import java.util.*;

import exceptions.ActionException;

/**
 * Created by shiv on 10/14/2016.
 * @author Shiv , Trae X. Lewis
 */

public class ActionCollectionService
{
    /*
       A Collection of Different Actions added to HashMap
       @actions     a HashMap of String as a Key and Action as a Value
       @insertAction    adds Action to HashMap
       @removeAction    removes Action from the HashMap

     */
    private static Map<String,Action> actions = new HashMap<String, Action>();
    private static ActionCollectionService Instance;


    public static ActionCollectionService getInstance()
    {
        if (Instance == null)
        {
            Instance = new ActionCollectionService();
        }
        return Instance;
    }

    /*
        takes String and an Action and tries to add to HashMap
        Throws Exception if duplicate Key(String) and duplicate value(Action)
     */
    public void insertActions(String actionName, Action action) throws ActionException
    {
    	actionName = actionName.toUpperCase();
    	
        if (actions.keySet().contains(actionName)) 
        {
            throw new ActionException("ActionCollectionService::insertActions::39 - Action Key Exists: " + actionName);
        }
        
        if (duplicateAction(action))
        {
            throw new ActionException("ActionCollectionService::insertActions::39 - Duplicate Action Exists: " + action);
        }
        
        actions.put(actionName, action);
    }


    /*
	    Removes Action using the Key
	 */
	public void removeAction(String actionName) throws ActionException 
	{
		actionName = actionName.toUpperCase();
		
	    if(!actions.containsKey(actionName.toUpperCase())) 
	    {
	        throw new ActionException("Action Named: " + actionName + "Not Found");	        
	    }

	    actions.remove(actionName);
	}

	/**
	 * Returns an action from the ActionCollectionService.
	 * @param actionName Name of the action to query
	 * @return Action matching name if found, else returns null.
	 */
	public Action getAction(String actionName)
	{
		actionName = actionName.toUpperCase();
		Action action = actions.get(actionName);
		
		return action;
	}
	
	public boolean containsAction(Action action)
	{
		boolean result = false;
		
		if(actions.containsValue(action))
		{
			result = true;
		}
		
		return result;
	}
	
	public boolean containsKey(String key)
	{
		boolean result = false;
		
		if(actions.containsKey(key.toUpperCase()))
		{
			result = true;
		}
		
		return result;
	}

	/**
     * This method checks for duplicate actions in the HashMap.
     * @param action
     * @return true if Action is found, false otherwise
     */
    private boolean duplicateAction(Action action) 
    {
    	boolean result = false;
    	
        for (Action value: actions.values())
        {
        	if(value.equals(action))
        	{
        		result = true;
        	}
        }
        return result;
    }
    
    /*
        @returns String Representation of the Collections of the Action
     */
    public String toString()
    {
        String temp ="";

        for (String key: actions.keySet() )
        {
            temp +="\nAction Key: " + key.toString()  + "\t\t" + actions.get(key).toString();
        }
        return temp;
    }
}
