package controllers;

import models.*;
import services.RuleCollectionService;

/**
 * Created by Mike on 11/27/2016.
 */
public class RuleController {
    public static RuleController INSTANCE;
    public static RuleCollectionService ruleSVC = RuleCollectionService.getInstance();

    public RuleController() {}

    public static RuleController getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new RuleController();
        }
        return INSTANCE;
    }

    public static Constraint addConstraint(String lhs, Operator o, String rhs)
    {
        Constraint c = new Constraint(lhs, o, rhs, LogicalConjunction.NONE);
        // TODO: Will need to specify LogicalConjunction in the future
        return c;
    }

    public static ConstraintList addConstraintList(Constraint c)
    {
        return new ConstraintList(c);
    }

    public static ConditionalElement addConditionalElement(ConstraintList cl)
    {
        ConditionalElement ce = new ConditionalElement("Entity", cl);
        return ce;
    }

    public static ConditionalElementList addConditionalElementList(ConditionalElement ce)
    {
        ConditionalElementList cel = new ConditionalElementList();
        cel.add(ce);
        return cel;
    }

    public static Rule createDefaultRule()
    {
        return new Rule();
    }

    public static Rule setRuleFields(String title, ConditionalElementList cel, Action action)
    {
        return new Rule(title, cel, action);
    }

    public static void addRuleToCollection(Rule r)
    {
        ruleSVC.put(r.getTitle(), r);
    }

}
