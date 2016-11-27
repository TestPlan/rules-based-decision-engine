package controllers;

import models.Action;
import services.ActionCollectionService;

import java.util.ArrayList;

/**
 * Controller for interfacing between the + New Action view and the Action model.
 *
 * @author Michael Crinite
 * @version 11/17/16
 */
public class ActionController
{
    public static ActionController INSTANCE;
    ActionCollectionService acr = ActionCollectionService.getInstance();

    public ActionController() {}

    public static ActionController getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new ActionController();
        }
        return INSTANCE;
    }

    public void newAction(String actionName, String actionDescription)
    {
        Action action = new Action(actionDescription);

        acr.put(actionName, action);
    }

    public void retrieveActions()
    {
        ArrayList<Action> list = new ArrayList<Action>();
    }
}
