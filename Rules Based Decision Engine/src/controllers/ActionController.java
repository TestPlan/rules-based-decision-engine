package controllers;

import models.Action;
import services.ActionCollectionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Controller for allowing the user to access Action objects without directly interacting with them.
 *
 * @author Michael Crinite
 * @version 11/17/16
 */
public class ActionController
{
    public static ActionController INSTANCE;
    ActionCollectionService acs = ActionCollectionService.getInstance();

    /**
     * Default class constructor for type ActionController
     */
    public ActionController() {}

    /**
     * Returns the Class instance if it exists, creates one if it does not
     * @return The single instance of the ActionController class
     */
    public static ActionController getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new ActionController();
        }
        return INSTANCE;
    }

    /**
     * Creates an Action and adds it to the Action Collection Service
     *
     * @param actionName Key to store Action in the Collection service with
     * @param actionDescription String to declare in Action object creation
     */
    public void newAction(String actionName, String actionDescription)
    {
        Action action = new Action(actionDescription);

        acs.put(actionName, action);
    }

    /**
     * Retrieve all existing Actions from the Collection service
     * @return A String[] of all Actions in the collection
     */
    public String[] retrieveActions()
    {
        return acs.getActions();
    }

    /**
     * Retrieve a single Action from the Collection service
     * @param key Action key to search for
     * @return Action stored by the given key
     */
    public Action getAction(String key)
    {
        return acs.get(key);
    }

    /**
     * Remove all Actions from the collection
     */
    public void clearActions()
    {
        acs.clear();
    }

    /**
     * Removes a single Action from the collection
     * @param key Action key to search for
     * @return The removed action
     */
    public Action removeAction(String key)
    {
        return acs.remove(key);
    }

    /**
     * Checks that an Action exists matching either the given key or given description
     * @param Action String to search for. If the string matches any in an Action the collection, the Action exists
     * @return True if an Action exists with the specified Action String
     */
    public boolean exists(String Action)
    {
        Action temp;
        for(String s : retrieveActions())
        {
            temp = getAction(s);
            if(temp.getAction().equalsIgnoreCase(Action.trim()))
            {
                return true;
            }
        }
        return false;
    }
}
