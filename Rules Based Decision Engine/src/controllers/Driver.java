package controllers;

import services.InputReaderService;
import services.ObjectCollectionService;

import java.io.File;


/**
 *
 *
 * @author Ian Markind, Trae X. Lewis
 */
public class Driver
{
    public static void main(String[] args)
    {
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
        // ObjectData od = new ObjectData("Temperature", "Double", 37.6 );

       // FireRules fr = new FireRules("avoid.drl", od);

    	System.out.println(collection_svc);

        read_svc.readJSONFile(PATH);

        System.out.println(collection_svc);

    }
}
