package controllers;

import helpers.CreateDroolsFile;
import models.*;
import services.EntityCollectionService;
import services.FileWriterService;
import services.RuleCollectionService;

import java.io.File;
import java.util.ArrayList;

/**
 * Allows a user to interface between the Rules (and their parts) without directly interacting with them
 */
public class RuleController {
    //Fields and services
    public static RuleController INSTANCE;
    public static RuleCollectionService ruleSVC = RuleCollectionService.getInstance();
    public static EntityCollectionService entity_svc = EntityCollectionService.getInstance();
    public static File drlFile = null;

    /**
     * Default constructor of type RuleController
     */
    public RuleController() {}

    /**
     * Retrieves the single instance of RuleController or creates one if none exist
     * @return The single instance of RuleController
     */
    public static RuleController getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new RuleController();
        }
        return INSTANCE;
    }

    /**
     * Creates a Constraint object from its parts.
     *
     * @see models.Constraint
     *
     * @param lhs Left hand operand
     * @param o Operator
     * @param rhs Right hand operand
     * @return A new constraint object
     */
    public static Constraint addConstraint(String lhs, Operator o, String rhs)
    {
        Constraint c = new Constraint(lhs, o, rhs, LogicalConjunction.NONE);
        // TODO: Will need to specify LogicalConjunction in the future
        return c;
    }

    /**
     * Creates a ConstraintList object from its parts
     * Currently supports a ConstraintList with a single Constraint object
     *
     * @see models.ConstraintList
     *
     * @param c The Constraint to add to the list
     * @return The new ConstraintList
     */
    public static ConstraintList addConstraintList(Constraint c)
    {
        return new ConstraintList(c);
    }

    /**
     * Creates a Conditional Element from its parts
     * Currently supports a ConditionalElement containing only a single ConstraintList
     *
     * @see ConditionalElement
     *
     * @param cl The ConstraintList to add to the Conditional Element
     * @return The new ConditionalElement
     */
    public static ConditionalElement addConditionalElement(ConstraintList cl)
    {
        ConditionalElement ce = new ConditionalElement("Entity", cl);
        return ce;
    }

    /**
     * Creates a ConditionalElementList from its parts
     * Currently supports adding only a single ConditionalElement to a list
     *
     * @see models.ConditionalElementList
     *
     * @param ce The ConditionalElement to add to the list
     * @return The new ConditionalElementList
     */
    public static ConditionalElementList addConditionalElementList(ConditionalElement ce)
    {
        ConditionalElementList cel = new ConditionalElementList();
        cel.add(ce);
        return cel;
    }

    /**
     * Utilizes the RuleActivation class to fire all rules with all Entities as input
     *
     * @see controllers.RuleActivation
     *
     * @param keys (Currently not required) A list of Entities to add to the rule firing
     */
    public void fireAllRules(ArrayList<String> keys)
    {
        Entity[] entities = EntityCollectionService.getInstance().getAllEntities();//EntityController.getINSTANCE().getEntityArray(keys);
        RuleActivation ra = new RuleActivation(Driver.chooseFileLocation(),entities);
    }

    /**
     * Creates a default empty Rule object
     *
     * @return A new, empty Rule
     */
    public static Rule createDefaultRule()
    {
        return new Rule();
    }

    /**
     * Sets the fields of a Rule object
     *
     * @see models.Rule
     *
     * @param title The Rule's title and key
     * @param salience The Rule's salience, or priority
     * @param cel The Rule's ConditionalElementList
     * @param actionList The list of Actions to be fired by the rule
     * @return The new Rule object with the specified fields
     */
    public static Rule setRuleFields(String title, Integer salience, ConditionalElementList cel, ActionList actionList)
    {
        return new Rule(title, salience, cel, actionList);
    }

    /**
     * Puts a Rule in the collection
     *
     * @param r The Rule object to add to the collection
     */
    public static void addRuleToCollection(Rule r)
    {
        ruleSVC.put(r.getTitle(), r);
    }

    /**
     * Creates a .drl file corresponding to the given Rule object
     *
     * @param r Rule from which to create a file
     */
    public static void createDroolsFileFromRule(Rule r)
    {
        makeFile(r.getTitle());
        FileWriterService.getInstance().writeToFile(drlFile, r, true);
    }

    /**
     * Creates a .drl file if one does not already exist
     *
     * @param fileName Filename to look for or create
     */
    public static void makeFile(String fileName)
    {
        if(drlFile == null)
        {
            CreateDroolsFile cdf = new CreateDroolsFile();
            drlFile = cdf.makeDroolsFile(fileName);
        }
    }

    /**
     * Appends to an existing .drl file
     *
     * @param f File to append to
     * @param r Rule to append to file
     */
    public static void appendToDroolsFile(File f, Rule r)
    {
        FileWriterService.getInstance().writeToFile(f, r, true);
    }
    public Object[] getAllRules()
    {
        return ruleSVC.toArray();
    }

    public String getRuleTextFromKey(String key){
        return ruleSVC.get(key).toString();
    }

}
