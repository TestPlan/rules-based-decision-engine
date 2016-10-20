package controllers;

//import helpers.CreateDroolsFile;
//import models.Rule;
import helpers.CreateDroolsFile;
import models.Rule;
import services.DataConversionService;
import services.ObjectCollectionService;


/**
 * Created by Ian Markind on 10/8/2016.
 */
public class Driver {
    public static void main(String[] args) {
//        Rule rule = new Rule("Rule Title", "Rule Action");
//        System.out.println(rule);
//        CreateDroolsFile cdf = new CreateDroolsFile("testing");
//       cdf.makeDroolsFile();
    	
    	DataConversionService data_svc = DataConversionService.getInstance();
    	ObjectCollectionService collection_svc = ObjectCollectionService.getInstance();
        	
    }
}
