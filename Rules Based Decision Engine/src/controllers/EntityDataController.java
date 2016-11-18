package controllers;

import models.Entity;
import models.Data;
import services.EntityCollectionService;
import services.ObjectCollectionService;

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
        EntityCollectionService.getInstance().insertEntity(e.getName(), e);

        for(String s : e.getData().keySet())
        {
            ObjectCollectionService.getInstance().insertDataObject(s, e.getData().get(e));
        }
    }

    // TODO: Add functionality for removing Entity/Data?
}
