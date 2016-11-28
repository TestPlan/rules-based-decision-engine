package controllers;

import models.Entity;
import services.EntityCollectionService;
import services.FileReaderService;

import java.io.File;
import java.util.Set;

/**
 * Created by Mike on 11/17/2016.
 */
public class EntityController
{
    public static EntityController INSTANCE;
    public static EntityCollectionService svc = EntityCollectionService.getInstance();

    public EntityController(){}

    public static EntityController getINSTANCE() {
        if(INSTANCE == null)
        {
            INSTANCE = new EntityController();
        }
        return INSTANCE;
    }

    public static void addEntity(File datafile)
    {
        FileReaderService fr = FileReaderService.getInstance();

        try {
            if (datafile.getAbsolutePath().toUpperCase().endsWith(".JSON")) {
                fr.readJsonFile(datafile.getAbsolutePath());
            } else if (datafile.getAbsolutePath().toUpperCase().endsWith(".CSV")) {
                //This method will have to call a CSV-specific method
                //TODO: Add code to parse a CSV file
            } else if (datafile.getAbsolutePath().toUpperCase().endsWith(".TXT")) {
                fr.readTextFile(datafile.getAbsolutePath());
            }
        }
        catch(NullPointerException n)
        {
            System.out.println(n.getMessage());
        }
    }

    /**
     * Add an Entity to the collection service
     * @param e Entity to add
     */
    public static void addEntity(Entity e)
    {
        svc.put(e);
    }

    /**
     * Remove an Entity from the collection service by its key
     * @param key
     */
    public static void removeEntity(String key)
    {
        svc.remove(key);
    }

    /**
     * Retrieve an Entity from the collection service by its key
     * @param key
     */
    public static void retrieveEntity(String key)
    {
        svc.get(key);
    }

    public static String[] retrieveFields(String key)
    {
        return svc.retrieveFields(key);
    }
}
