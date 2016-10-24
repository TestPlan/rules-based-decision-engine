package services;

import Exceptions.ActionException;
import models.Action;

import java.util.*;


/**
 * Created by shiv on 10/14/2016.
 */

public class ActionCollectionServices
{
    /*
       A Collection of Different Actions added to HashMap
       @actions     a HashMap of String as a Key and Action as a Value
       @insertAction    adds Action to HashMap
       @removeAction    removes Action from the HashMap

     */
    private static Map<String, Action> actions = new HashMap<String, Action>();
    private static ActionCollectionServices Instance;


    public static ActionCollectionServices getInstance()
    {
        if (Instance == null)
        {
            Instance = new ActionCollectionServices();
        }
        return Instance;
    }

    /*
        takes String and an Action and tries to add to HashMap
        Throws Exception if duplicate Key(String) and duplicate value(Action)
     */
    public void insertActions(String ActionName, Action action) throws ActionException
    {
        if (actions.keySet().contains(ActionName)) //Key
        {
            throw new ActionException("---Duplicate ActionName: " + ActionName + "\n");
        }
        else
        {
            if (duplicateAction(action))
            { //Value
                throw new ActionException("---Duplicate Action: " + action + "\n");
            }
        }
        actions.put(ActionName, action);
    }

    /*
        takes Action and Throws Exception if duplicate Action Exists
     */
    private boolean duplicateAction(Action action)
    {

        for (String key : actions.keySet())
        {
            if (key.isEmpty())
            {
                return false;
            }
            if (actions.get(key).equals(action))
            {
                return true;
            }
        }
        return false;
    }


    /*
        Removes Action using the Key
     */
    public void removeAction(String actionName) throws ActionException
    {
        if (actions.containsKey(actionName))
        {
            actions.remove(actionName);
        }
        else
        {
            throw new ActionException("Action Named: " + actionName + "Not Found");
        }
    }

    /*
        @returns String Representation of the Collections of the Action
     */
    public String toString()
    {
        String temp = "";
        Iterator iterator = actions.entrySet().iterator();
        for (String key : actions.keySet())
        {
            temp += "\nAction Key: " + key.toString() + actions.get(key).toString();
        }
        return temp;
    }
}
