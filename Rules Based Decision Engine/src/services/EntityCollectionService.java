package services;

import models.Entity;

import java.util.HashMap;

/**
 * Created by Mike on 11/17/2016.
 */
public class EntityCollectionService {
    private EntityCollectionService INSTANCE;
    private static HashMap<String, Entity> entity_list = new HashMap<String, Entity>();

    public EntityCollectionService(){}

    /**
     * Creates the single instance of this Class, or returns the only existing one
     * @return The single instance of this class
     */
    public EntityCollectionService getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new EntityCollectionService();
        }
        return INSTANCE;
    }


}
