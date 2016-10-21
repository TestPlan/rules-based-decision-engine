package controllers;

import services.InputReaderService;
import services.ObjectCollectionService;


/**
 * Created by Ian Markind on 10/8/2016.
 * @author Ian Markind, Trae X. Lewis 
 */
public class Driver {
    public static void main(String[] args) {

        String file = System.getenv("RDE_HOME") + "/temp.json";
      
        InputReaderService read_svc = InputReaderService.getInstance();
        ObjectCollectionService collection_svc = ObjectCollectionService.getInstance();
        read_svc.readJSONFile(file);
    	
    	System.out.println(collection_svc);
    	

    }
    

}
