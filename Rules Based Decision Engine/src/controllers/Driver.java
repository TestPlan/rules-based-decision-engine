package controllers;

import models.*;
import helpers.*;
import services.FileWriterService;
import services.FileReaderService;
import services.ObjectCollectionService;

import java.io.File;
import java.util.ArrayList;


/**
 *
 *
 * @author Ian Markind, Trae X. Lewis, Michael Crinite
 */
public class Driver
{
    @SuppressWarnings("unused")
	public static void main(String[] args)
    {
  	

    	/**
    	 * NAME OF THE RULE
    	 */
    	String ruleName = "Test";
    	
    	/**
    	 * OBJECT DATA
    	 * - Represents data points that can be retrieved from an external source and stored in a data structure for later use
    	 */
    	models.ObjectData data1 = new models.ObjectData("TEMPERATURE", "DOUBLE", 98.6);
    	models.ObjectData data2 = new models.ObjectData("NAME" , "STRING" , "Mathew");
    	models.ObjectData data3 = new models.ObjectData("IS_HUNGRY" , "BOOLEAN" , true);
    	
    	/**
    	 * CONSTRAINTS
    	 * - Specific constraints placed on different pieces of ObjectData objects.
    	 */
    	Constraint constraint = new Constraint(data1, models.Operator.GREATER_THAN, 65.3 , models.LogicalConjunction.NONE);
    	Constraint constraint2 = new Constraint(data2, Operator.EQUAL_TO, "Trae", LogicalConjunction.AND);
    	
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
    	models.ConditionalElement cond1 = new models.ConditionalElement("$Test" , "TestScenario", list);
    	
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
    	models.Action action = new models.Action("Jump");
    	
    	/**
    	 * RULE
    	 * 
    	 */
    	Rule rule = new Rule(ruleName , condList , action);
    	
    	System.out.println(rule.toString());

    }
}
