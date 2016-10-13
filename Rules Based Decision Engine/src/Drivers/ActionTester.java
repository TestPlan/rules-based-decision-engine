package Drivers;

import models.Action;
import models.arrayOfActions;

/**
 * Created by shiv on 10/12/2016.
 */
public class ActionTester {
    public static void main(String args[]){

        arrayOfActions a = new arrayOfActions();
        Action a1 = new Action("Action-1", "Action 1 executed");
        Action a2 = new Action("Action-2", "Action 2 executed");

        System.out.println("-> " + a1);
        System.out.println("-> " + a2);


        a.add(a1);
        a.add(a2);
        System.out.println("Action List: " + a);

        a.remove(a1);

        System.out.println("After Remove Action List: " + a);
        a1.setAction("Action 1 modified");
        a2.setActionName("ActionName 2 Modified");
        a.add(a1);
        System.out.println("After Adding Action List: " + a);

    }

}
