package controllers;

import helpers.CreateDroolsFile;
import models.*;
import services.EntityCollectionService;
import services.FileWriterService;
import services.RuleCollectionService;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Mike on 11/27/2016.
 */
public class RuleController {
    public static RuleController INSTANCE;
    public static RuleCollectionService ruleSVC = RuleCollectionService.getInstance();
    public static EntityCollectionService entity_svc = EntityCollectionService.getInstance();
    public static File drlFile = null;

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

    public void fireAllRules(ArrayList<String> keys)
    {
        Entity[] entities = EntityCollectionService.getInstance().getAllEntities();//EntityController.getINSTANCE().getEntityArray(keys);
        RuleActivation ra = new RuleActivation(Driver.chooseFileLocation(),entities);
    }

    public static Rule createDefaultRule()
    {
        return new Rule();
    }

    public static Rule setRuleFields(String title, Integer salience, ConditionalElementList cel, ActionList actionList)
    {
        return new Rule(title, salience, cel, actionList);
    }

    public static void addRuleToCollection(Rule r)
    {
        ruleSVC.put(r.getTitle(), r);
    }

    public static void createDroolsFileFromRule(Rule r)
    {
        makeFile(r.getTitle());
        FileWriterService.getInstance().writeToFile(drlFile, r, true);
    }

    public static void makeFile(String fileName)
    {
        if(drlFile == null)
        {
            CreateDroolsFile cdf = new CreateDroolsFile();
            drlFile = cdf.makeDroolsFile(fileName);
        }
    }

    public Object[] getAllRules()
    {
        return ruleSVC.toArray();
    }

    public String getRuleTextFromKey(String key){
        return ruleSVC.get(key).toString();
    }

}
