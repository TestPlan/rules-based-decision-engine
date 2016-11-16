package controllers;

import models.Entity;
import services.FileReaderService;

import java.util.ArrayList;

/**
 * Created by shiv on 11/12/2016.
 */
public class SampleReader
{

    public static void main(String args[])
    {
        FileReaderService fr = new FileReaderService();
        Entity entity = fr.readJsonFile("./res/data/temp.json");

        System.out.println(entity);

    }
}
