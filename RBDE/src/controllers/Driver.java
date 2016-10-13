package controllers;

import java.io.File;

import helpers.InputReader;
import models.ObjectData;
//import helpers.CreateDroolsFile;
//import models.Rule;
import services.DataConversionService;
import services.FileReaderService;
import services.ObjectCollectionService;


/**
 * Created by Ian Markind on 10/8/2016.
 */
public class Driver {
    public static void main(String[] args) {
     //   Rule rule = new Rule("Rule Title", "Rule Action");
     //   System.out.println(rule);
     //   CreateDroolsFile cdf = new CreateDroolsFile("testing");
     //   cdf.makeDroolsFile();
       // InputReader reader = new InputReader();
        File f = new File("./src/temp.txt");
    	InputReader reader = new InputReader(f);
    	ObjectCollectionService collection_svc = ObjectCollectionService.getInstance();
    	
    	ObjectData data = collection_svc.retrieveRuleObject("distance");
    	System.out.println(collection_svc);
    	
    	
    	

        	
    }
}
