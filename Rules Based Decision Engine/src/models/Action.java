package models;

/**
 * Created by shiv on 10/11/2016.
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


    public boolean equals(Object obj)
    {
        if (obj instanceof Action)
        {
            Action temp = (Action) obj;

            if (temp == null)
            {
                return false;
            }

            if (!temp.getAction().equals(this.action))
            {
                return false;
            }
            return true;
        }
        return false;
    }

    public String toString()
    {
        return "Action Result: " + this.getAction() + "\n";
    }


}
