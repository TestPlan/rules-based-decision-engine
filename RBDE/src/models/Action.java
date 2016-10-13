package models;

/**
 * Created by shiv on 10/11/2016.
 */
public class Action {



    private String ActionName;
    private String Action;

    public Action (String actionName, String action){
        this.ActionName = actionName;
        this.Action = action;
    }

    public String getActionName() {
        return this.ActionName;
    }

    public String getAction() {
        return this.Action;
    }

    public void setAction(String action) {
        Action = action;
    }
    public void setActionName(String actionName) {
        ActionName = actionName;
    }

    public String toString(){
        return "Action Name: " + this.getActionName()
                + " " + "\nAction Result: " + this.getAction();

    }

}
