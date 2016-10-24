package controllers;

import services.RulesReader;

/**
 * Created by shiv on 10/23/2016.
 */
public class FileReaderTester
{
     public static void main(String[] args)
    {
        RulesReader r = new RulesReader("./src//rules//","avoid.drl");
       r.readDroolsFile();
    }
}
