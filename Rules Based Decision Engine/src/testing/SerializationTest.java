package testing;

import models.Constraint;
import models.LogicalConjunction;
import models.Operator;
import models.Rule;
import services.ObjectCollectionService;
import services.SerializationService;

import javax.swing.*;

/**
 * Created by Mike on 11/3/2016.
 */
public class SerializationTest
{
    public static void main(String[] args) throws Exception{

    	ObjectCollectionService obj_svc = ObjectCollectionService.getInstance();
        String ruleName = "Test";

        System.out.println(ruleName.getClass().getName());

        /**
         * OBJECT DATA
         * - Represents data points that can be retrieved from an external source and stored in a data structure for later use
         */
        obj_svc.put("Temperature", 15.5);
        obj_svc.put("Name", "Mathew");
        obj_svc.put("IS_HUNGRY", true);


        /**
         * CONSTRAINTS
         * - Specific constraints placed on different pieces of ObjectData objects.
         */
        Constraint constraint = new Constraint(obj_svc.get("Temperature"), models.Operator.GREATER_THAN, 65.3 , models.LogicalConjunction.NONE);
        Constraint constraint2 = new Constraint(obj_svc.get("Name"), Operator.EQUAL_TO, "Trae", LogicalConjunction.AND);

        /**
         * CONSTRAINT LIST
         * - Creates an ArrayList of all constraints for the specific rule.
         */
        models.ConstraintList list = new models.ConstraintList();
        list.add(constraint);
        list.add(constraint2);

        /**
         * CONDITIONAL ELEMENT
         * - Creates the conditional element of a rule which consists of the pattern binding, pattern type and list of constraints
         */
        models.ConditionalElement cond1 = new models.ConditionalElement("$o" , "ObjectData", list);

        /**
         * CONDITIONAL ELEMENT LIST
         * - Consists of an ArrayList of conditional elements of a rule
         */
        models.ConditionalElementList condList = new models.ConditionalElementList();
        condList.add(cond1);

        /**
         * ACTIONS
         * - Basic action to be done if conditions are true.
         */
        models.Action action = new models.Action("System.out.println(\"Jump\");");

        /**
         * RULE
         *
         */
        //TODO: Fix Errors salience
       // Rule rule = new Rule(ruleName , salience, condList , actionList);

        SerializationService ss = SerializationService.getInstance();
       // ss.serialize(rule, chooseFileLocation() + rule.getTitle());

        Rule rule1 = new Rule();
        rule1 = (Rule) ss.deserialize(chooseFileLocation());

       // System.out.println(rule.toString());
        System.out.println(rule1.toString());

    }

    /**
     * This method's purpose is to give the user access to their file system so that they can choose the file
     * they want to deserialize, or the directory they want their serialized file in.
     */
    public static String chooseFileLocation(){
        String fileLoc = new String();

        JFileChooser fc = new JFileChooser(".");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.showOpenDialog(null);
        fileLoc = fc.getSelectedFile().getAbsolutePath() + "\\";

        return fileLoc;
    }

}
