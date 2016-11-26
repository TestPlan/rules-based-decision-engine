package controllers;

import models.Entity;
import services.EntityCollectionService;

/**
 * Created by Mike on 11/17/2016.
 */
public class EntityDataController
{
    public static EntityDataController INSTANCE;

    public EntityDataController(){}

    public static EntityDataController getINSTANCE() {
        if(INSTANCE == null)
        {
            INSTANCE = new EntityDataController();
        }
        return INSTANCE;
    }

    public static void addEntity(Entity e)
    {
        EntityCollectionService.getInstance().put(e.getEntityName(), e);

    }

    // TODO: Add functionality for removing Entity/Data?
}
