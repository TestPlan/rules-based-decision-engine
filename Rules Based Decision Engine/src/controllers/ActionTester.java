package controllers;

import models.Action;
import services.ActionCollectionService;

/**
 * Created by shiv on 10/12/2016.
 */
public class ActionTester
{
    // static JFrame mainframe = new JFrame("Actions Collection");
    public ActionTester()
    {

    }

    public static void main(String args[]) throws Exception
    {
        //        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //
        //        mainframe.setVisible(true);
        //        mainframe.setSize(1000,500);
        //
        //        mainframe.pack();
        ActionCollectionService a = new ActionCollectionService();
        Action a1 = new Action("A1-Executed");
        Action a2 = new Action("A2-Executed");
        Action a3 = new Action("A2-Executed");
        System.out.println("-> " + a1);
        System.out.println("-> " + a2);
        System.out.println("-> " + a3);


        a.put("Action-1", a1);

        System.out.println("\nAction List adding a1: " + a);
        a.put("Action-2", a2);

        System.out.println("Action List adding a2: " + a);

        a.remove("Action-1");

        System.out.println("After Remove Action a1 List: " + a);


        a1.setAction("Action 1 modified");
        a.put("Action-3", a3);

        a.put("Action-1", a1);

        System.out.println("After Adding Action List: " + a);


            a.remove("Action-5");
         System.out.println("Tried Removing A-5");
    }
}
