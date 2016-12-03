package controllers;

import helpers.CreateDroolsFile;
import models.*;
import services.FileWriterService;
import services.RuleCollectionService;

import java.io.File;

/**
 * A Controller to allow users to interact with Rule objects (and all its parts) without directly accessing them
 *
 * @author Michael Crinite
 * @version 12/02/2016
 */
public class RuleController {
    public static RuleController INSTANCE;
    public static RuleCollectionService ruleSVC = RuleCollectionService.getInstance();

    /**
     * Default constructor for objects of class RuleController
     */
    public RuleController() {}

    /**
     * Retrieves the singeton RuleController, creates one if none exist
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
     * Creates a single object of type Contraint
     * @param lhs Left-hand-side of Constraint (see {@link models.Constraint})
     * @param o Operator of Constraint
     * @param rhs Right-hand-side of Constraint (see {@link models.Constraint})
     * @return New Constraint from arguments
     */
    public static Constraint addConstraint(String lhs, Operator o, String rhs)
    {
        Constraint c = new Constraint(lhs, o, rhs, LogicalConjunction.NONE);
        // TODO: Will need to specify LogicalConjunction in the future
        return c;
    }

    /**
     * Creates a ConstraintList to use within the rule
     * @param c Constraint to add to ConstraintList (see {@link models.ConstraintList})
     * @return Newly generated ConstraintList containing c
     */
    public static ConstraintList addConstraintList(Constraint c)
    {
        return new ConstraintList(c);
    }

    /**
     * Creates a ConditionalElement from the objects in ConstraintList cl
     * @param cl A ConstraintList containing Constraints (see {@link models.ConditionalElement})
     * @return A ConditionalElement containing cl
     */
    public static ConditionalElement addConditionalElement(ConstraintList cl)
    {
        ConditionalElement ce = new ConditionalElement("Entity", cl);
        return ce;
    }

    /**
     * Creates a ConditionalElementList containing the ConditionalElement ce
     * @param ce A ConditionalElement (see {@link models.ConditionalElementList})
     * @return A ConditionalElementList containing ce
     */
    public static ConditionalElementList addConditionalElementList(ConditionalElement ce)
    {
        ConditionalElementList cel = new ConditionalElementList();
        cel.add(ce);
        return cel;
    }

    /**
     * Creates a Rule object with default field values
     * @return The newly created Rule
     */
    public static Rule createDefaultRule()
    {
        return new Rule();
    }

    /**
     * Returns a Rule object containing the given values
     * @param title Rule title
     * @param cel See {@link models.ConditionalElementList}
     * @param action See {@link models.Action}
     * @return
     */
    public static Rule setRuleFields(String title, ConditionalElementList cel, Action action)
    {
        return new Rule(title, cel, action);
    }

    /**
     * Adds a single Rule to the collection
     * @param r Rule to add to collection
     */
    public static void addRuleToCollection(Rule r)
    {
        ruleSVC.put(r.getTitle(), r);
    }

    /**
     * Creates a Drools .drl file from the given Rule
     * @param r Rule from which to create .drl file
     */
    public static void createDroolsFileFromRule(Rule r)
    {
        CreateDroolsFile cdf = new CreateDroolsFile();
        File f = cdf.makeDroolsFile(r.getTitle());
        FileWriterService.getInstance().writeToFile(f, r, false);
    }

    /**
     * Returns an array of all existing rules
     * @return an array of all existing rules
     */
    public Object[] getAllRules()
    {
        return ruleSVC.toArray();
    }

    /**
     * Retrieves a Rule from the collection and returns its values as text
     * @param key Rule key to search for in the collection
     * @return The text of the given Rule if it exists
     */
    public String getRuleTextFromKey(String key){
        return ruleSVC.get(key).toString();
    }

    /**
     * Removes a Rule from the collection
     * @param key Rule key to search for
     * @return The Rule, if it exists
     */
    public Rule removeRule(String key)
    {
        return ruleSVC.remove(key);
    }

    /**
     * Removes all Rule objects from the collection
     */
    public void clear()
    {
        ruleSVC.clear();
    }

}
