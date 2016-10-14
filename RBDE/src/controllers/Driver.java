package controllers;

//import helpers.CreateDroolsFile;
//import models.Rule;
import services.TextFileService;

import java.util.Map;

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
    	
        String file = "./src/temp.txt";
        TextFileService par_svc = TextFileService.getInstance();
        ObjectCollectionService collection_svc = ObjectCollectionService.getInstance();
        
        par_svc.parseTextFile(file);
    	
    	System.out.println(collection_svc);
    	
/*    	Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                              envName,
                              env.get(envName));
        }
        
        System.out.println(System.getenv("RBDE_HOME"));*/
    	

        	
    }
}
