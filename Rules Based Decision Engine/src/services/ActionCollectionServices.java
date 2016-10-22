package services;
import Exceptions.ActionException;
import models.Action;

import java.util.*;

/**
 * Created by shiv on 10/14/2016.
 */

public class ActionCollectionServices
{
    // HashMap
    //TODO Avoid Adding Duplicate Action
    private static Map<String,Action> actions = new HashMap<String, Action>();
    private static ActionCollectionServices Instance;


    public static ActionCollectionServices getInstance()
    {
        if (Instance == null)
        {
            Instance = new ActionCollectionServices();
        }
        return Instance;
    }

    public void insertActions(String ActionName, Action action) throws ActionException
    {
        if (actions.keySet().contains(ActionName)) //Key
        {
            throw new ActionException("---Duplicate ActionName: " + ActionName + "\n");
        }
        else
        {
            if (duplicateAction(action)){ //Value
                throw new ActionException("---Duplicate Action: " + action + "\n");
            }
        }
        actions.put(ActionName, action);
    }

    private boolean duplicateAction(Action action) 
    {

        for (String key: actions.keySet())
        {
            if(key.isEmpty())
            {
                return false;
            }
            if(actions.get(key).equals(action))
            {
                return true;
            }
        }
        return false;
    }

    public void removeAction(String actionName)
    {
        actions.remove(actionName);
    }


    public String toString()
    {
        String temp ="";
        Iterator iterator = actions.entrySet().iterator();
        for (String key: actions.keySet() ){
            temp +="\nAction Key: " + key.toString()  + actions.get(key).toString();
        }
        return temp;
    }

}