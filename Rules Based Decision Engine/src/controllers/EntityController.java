package controllers;

import models.Entity;
import org.codehaus.plexus.util.StringUtils;
import org.drools.core.util.HashTableIterator;
import services.EntityCollectionService;
import services.FileReaderService;
import services.ObjectCollectionService;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Allows a user to interface with Entity objects without directly interacting with them
 *
 * @author Michael Crinite
 * @version 1.0 12/15/2016
 */
public class EntityController
{
    //Fields
    public static EntityController INSTANCE;
    public static EntityCollectionService svc = EntityCollectionService.getInstance();

    /**
     * Default Constructor for type EntityController
     */
    public EntityController(){}

    /**
     * Retrieves the instance of EntityController or creates it if none exist
     * @return The Singleton instance of EntityController
     */
    public static EntityController getINSTANCE() {
        if(INSTANCE == null)
        {
            INSTANCE = new EntityController();
        }
        return INSTANCE;
    }

    /**
     * Creates a new Entity from a list of keys and their values
     * Parses each key to add it to an Object array which will be added to the ObjectCollectionService
     *
     * @param name The Entity's name
     * @param keys The list of keys representing the Entity's fields
     * @param values The values associated with the Entity's fields
     */
    public void createEntity(String name, String[] keys, HashSet<String> keyset, String[] values){
        //TODO: try/catch
        EntityCollectionService.getInstance().putTemp(new Entity(name, keyset));

        // Parse values for types
        Object[] toAdd = new Object[values.length];
        int i = 0;

        for (String s : values)
        {
            //TODO: add better regex
            if (s.equalsIgnoreCase("false")
                || s.equalsIgnoreCase("true"))
            {
                boolean b = Boolean.parseBoolean(s);
                toAdd[i] = b;
            }
            else if (StringUtils.isNumeric(s) && !s.equals(""))
            {
                if(s.contains(".")){
                    double d = Double.parseDouble(s);
                    toAdd[i] = d;
                }
                else
                {
                    int x = Integer.parseInt(s);
                    toAdd[i] = x;
                }
            }
            else if(s.length() == 1 && s.matches("[a-zA-Z]")){
                    char c = s.charAt(0);
                    toAdd[i] = c;
            }
            else
            {
                toAdd[i] = s;
            }

            i++;
        }

        //Finally add objects to the collection
        for(int index = 0; i < values.length; index++)
        {
            ObjectCollectionService.getInstance().put(keys[index], toAdd[index]);
        }
    }

    /**
     * Adds an Entity by parsing a file. The Entity creation is handled by the corresponding method
     * in the FileReaderService class.
     *
     * Currently supports json files in the proper format and txt files in the proper format.
     * The CSVReader functionality is currently not attached to the project.
     *
     * @param datafile File to parse
     */
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

    public static Entity[] getEntityArray(ArrayList<String> keys){
        Entity[] temp = new Entity[keys.size()];
        int index =0;
        Entity[] allEntities = svc.getAllEntities();
        for(Entity e: allEntities){
           if(keys.contains(e.getEntityName())){
               temp[index] = e;
               index++;
           }
        }
        return temp;
    }

    /**
     * Retrieve the objects in ObjectCollectionService corresponding to the fields in the Entity
     * @param key Entity to search for
     * @return A String array containing all the fields in an Entity
     */
    public static String[] retrieveFields(String key)
    {
        try {
            return svc.retrieveFields(key);
        }catch(NullPointerException e){
            return svc.retrieveTempFields(key);
        } // First attempts to search the main collection. On NullPointerException, searches temp collection
    }

    /**
     * Retrieves fields only from the temporary Collection in Object Collection Service
     * @param key Entity to search for
     * @return A String array containing its fields.
     */
    @Deprecated
    public static String[] retrieveTempFields(String key)
    {
        return svc.retrieveTempFields(key);
    }
}
