package controllers;

import models.Action;
import services.ActionCollectionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Allows a user to interface between the objects in the models package without directly interacting
 * with them.
 *
 * @author Michael Crinite
 * @version 11/17/16
 */
public class ActionController
{
    //Fields
    public static ActionController INSTANCE;                                //Singleton instance
    ActionCollectionService acr = ActionCollectionService.getInstance();    //Instance of ActionCollectionService

    /**
     * Default Constructor for type ActionController
     */
    public ActionController()
    {
    }

    /**
     * Retrieves the instance of ActionController or creates one if none exist
     *
     * @return The Singleton instance of ActionController
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
     * Creates a new Action object and adds it to the Action Collection Service
     *
     * @param actionName The name of the Action, to be used as its key in the colleciton
     * @param actionDescription The description of the Action (what does it do)
     */
    public void newAction(String actionName, String actionDescription)
    {
        Action action = new Action(actionDescription);

        acr.put(actionName, action);
    }

    /**
     * Retrieves all Action objects from the collection as an Array
     * @return An Array containing all Action objects in the collection
     */
    public String[] retrieveActions()
    {
        return acr.getActions();
    }

    /**
     * Retrieves a specific Action by its Key, if it exists in the collection
     *
     * @param key The key to search for
     * @return The Action in the collection matching that key.
     */
    public Action getAction(String key)
    {
        return acr.get(key);
    }
}
