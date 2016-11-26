package models;

import java.io.Serializable;

/**
 * Created by Shiv on 10/11/2016.
 * @version 1.50
 */
public class Action implements Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1607992443410504330L;
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
