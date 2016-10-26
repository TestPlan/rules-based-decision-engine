package models;

/**
 * Created by Shiv on 10/11/2016.
 * @version 1.50
 */
public class Action
{

    private String action;

    public Action()
    {
    }
    
    public Action(String action)
    {
        this.action = action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public String getAction()
    {
        return this.action;
    }

    /**
     * Sets the Action fields to null.
     */
    public void clear()
    {
    	this.action = null;
    }


    public String toString()
    {
        return action;
    }
}
