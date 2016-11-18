package controllers;

import exceptions.ActionException;
import models.Action;
import services.ActionCollectionService;

/**
 * Controller for interfacing between the + New Action view and the Action model.
 *
 * @author Michael Crinite
 * @version 11/17/16
 */
public class ActionController
{
    public static ActionController INSTANCE;

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
        ActionCollectionService acr = ActionCollectionService.getInstance();

        try
        {
            acr.insertActions(actionName, action);
        }
        catch(ActionException ae)
        {
            System.out.println(ae.getMessage());
        }
    }
}
