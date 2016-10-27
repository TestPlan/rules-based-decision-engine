package controllers;

import models.*;

/**
 * Created by MKII on 10/25/2016.
 */
public class ConditionalTester
{
    public static void main(String args[]) {
        ConstraintList constraints = new ConstraintList();
        ConditionalElement ce1 = new ConditionalElement(); //giving "null ( == )"
        constraints.add(new Constraint("up", Operator.NOT_EQUAL, "down", LogicalConjunction.NONE));
        ConditionalElement ce2 = new ConditionalElement("d", "direction", constraints);
        System.out.println(ce2.toString());

        constraints.add(new Constraint("left", Operator.EQUAL_TO, "left", LogicalConjunction.AND));
        ConditionalElement ce3 = new ConditionalElement("md", "moreDirection", constraints);
        System.out.println(ce3.toString());

        constraints.add(new Constraint("red", Operator.CONTAINS, "e", LogicalConjunction.AND));
        ConditionalElement ce4 = new ConditionalElement("old", constraints);
        System.out.println(ce4.toString());

        ConditionalElementList cel = new ConditionalElementList();
        cel.add(ce1);
        System.out.println(cel.getConditionalElementList().size());
        cel.add(ce2);
        System.out.println(cel.getConditionalElementList().size());
        cel.add(ce3);
        System.out.println(cel.getConditionalElementList().size());
        cel.add(ce4);
        cel.add(new ConditionalElement("rolled", constraints));
        System.out.println(cel.toString());
    }
}
