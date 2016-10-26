package controllers;

import models.*;
import helpers.*;
import services.FileWriterService;
import services.InputReaderService;
import services.ObjectCollectionService;

import java.io.File;


/**
 *
 *
 * @author Ian Markind, Trae X. Lewis, Michael Crinite
 */
public class Driver
{
    public static void main(String[] args)
    {
        /*
        final String FILE_NAME = "temp.json";
        // path to data file
        final String WORKSPACE = new File("").getAbsolutePath();
        final String PATH = WORKSPACE + "/res/data/" + FILE_NAME;

        InputReaderService read_svc = InputReaderService.getInstance();
        ObjectCollectionService collection_svc = ObjectCollectionService.getInstance();

       // String file = "./src/temp.txt";
       // TextFileService par_svc = TextFileService.getInstance();
       // ObjectCollectionService collection_svc = ObjectCollectionService.getInstance();

       // par_svc.parseTextFile(file);
        // System.out.println(collection_svc);

        ObjectData od = new ObjectData("Temperature", "Double", 37.6 );

        FireRules fr = new FireRules("avoid.drl", od);

    	System.out.println(collection_svc);
        read_svc.readJSONFile(PATH);
        System.out.println(collection_svc);

        // Basic testing of Constraint and ConstraintList classes
        ConstraintList cl = new ConstraintList();
        Constraint c = new Constraint("4", Operator.NOT_EQUAL, "5", LogicalConjunction.AND);
        Constraint c2 = null;
        cl.add(new Constraint("5", Operator.LESS_THAN, "6", LogicalConjunction.OR));
        cl.add(new Constraint("Temp.getTemp()", Operator.GREATER_EQUAL, "80", LogicalConjunction.NONE));
        cl.add(new Constraint("2100", Operator.EQUAL_TO, "myPurchase.getTotal()", LogicalConjunction.AND));
        cl.add(new Constraint("21", Operator.GREATER_EQUAL, "Person.getAge()", LogicalConjunction.AND));
        cl.add(new Constraint("55", Operator.GREATER_EQUAL, "Person.getAge()", LogicalConjunction.AND));
        cl.add(c);
        cl.add(new Constraint("Person.getPerson(\"Donald Trump\")", Operator.EQUAL_TO, "Person.isBufoon()", LogicalConjunction.AND));
        System.out.println("Size of list: " + cl.getConstraintList().size());
        if (cl.remove(c)) { System.out.println("Constraint Removed"); } // remove Constraint by object
        System.out.println("Size of list: " + cl.getConstraintList().size());
        if (cl.remove(0)) { System.out.println("Constraint Removed"); } // remove Constraint by index
        System.out.println("Size of list: " + cl.getConstraintList().size());
        if (!cl.remove(5)) { System.out.println("cl[5] Not Removed"); } // remove Constraint by index, should fail
        System.out.println("Size of list: " + cl.getConstraintList().size());
        if (!cl.add(c2)) { System.out.println("null Constraint not added"); } // c2 should not be added
        System.out.println("Size of list: " + cl.getConstraintList().size());
        System.out.println(cl);
        */

        ConditionalElementList cl = new ConditionalElementList();
        Rule rule = new Rule("Test", cl, new Action("Action Result"));
        CreateDroolsFile creator = new CreateDroolsFile(rule.getTitle());
        creator.makeDroolsFile();

        FileWriterService.getInstance().writeFile(filegoeshere, rule);

    }
}
