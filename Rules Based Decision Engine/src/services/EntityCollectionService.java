package services;

import models.Data;
import models.Entity;

import java.util.HashMap;

/**
 * Created by Mike on 11/17/2016.
 */
public class EntityCollectionService {
    private static EntityCollectionService INSTANCE;
    private static HashMap<String, Entity> entity_map = new HashMap<String, Entity>();

    public EntityCollectionService(){}

    /**
     * Creates the single instance of this Class, or returns the only existing one
     * @return The single instance of this class
     */
    public static EntityCollectionService getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new EntityCollectionService();
        }
        return INSTANCE;
    }

    /**
     * Insert an Entity into the entity_list
     * @param key A descriptive, memorable key to attach to this Entity
     * @param e The entity to add
     * @return <code>True</code> if the add was successful
     */
    public static boolean insertEntity(String key, Entity e)
    {
        key = key.trim().toUpperCase();
        if(entity_map.containsKey(key))
        {
            return false;
        }
        else
        {
            entity_map.put(key, e);
        }

        return true;
    }

    /**
     * Retrieve an Entity from the collection by its key
     * @param key Key of the Entity to retrieve
     * @return The Entity attached to the given key
     */
    public static Entity retrieveEntity(String key)
    {
        key = key.trim().toUpperCase();
        if (entity_map.containsKey(key))
        {
            return entity_map.get(key);
        }
        else
        {
            return null;
        }
    }

    public Entity removeDataObject(String str)
    {
        return entity_map.remove(str.toUpperCase());
    }

    /**
     * Clears the collection of entities.
     */
    public static void clearEntityService()
    {
        entity_map.clear();
    }

    /**
     * Formats Entities to print
     * @return
     */
    public String toString()
    {
        String temp = null;
        for (String key : entity_map.keySet())
        {
            entity_map.get(key).toString();
        }
        return temp;
    }


}
