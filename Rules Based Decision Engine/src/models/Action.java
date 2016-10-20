package models;

import javax.swing.*;

/**
 * Created by shiv on 10/11/2016.
 */
public class Action {

    private String Action;

    public Action() {}

    public Action(String action) {
        this.Action = action;
    }


    public String getAction() {

        return this.Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String toString() {
        return "\nAction Result: " + this.getAction();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Action) {

            Action temp = (Action) obj;


            if (temp == null) {
                return false;
            }

            if (!temp.Action.equals(this.Action)) {
                return false;
            }
            return true;
        }
        return false;
    }



}
