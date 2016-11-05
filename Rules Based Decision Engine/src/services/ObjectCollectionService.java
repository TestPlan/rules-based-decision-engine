package services;

import java.util.HashMap;

import models.Data;

/**
 * Static class designed to store all rule object data and any methods designed to manipulate the data.
 *
 * @author Trae X. Lewis
 * @version 2.0 10/20/2016
 */
public class ObjectCollectionService
{


    private static HashMap<String, Data> data_list = new HashMap<String, Data>();
    private static ObjectCollectionService INSTANCE;


    private ObjectCollectionService()
    {
    }


    /**
     * Creates new instance of ObjectCollectionService if INSTANCE == null.
     *
     * @return Instance of this class.
     */
    public static ObjectCollectionService getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new ObjectCollectionService();

        }
        return INSTANCE;
    }


    /**
     * Inserts <String, Data> pair into HashMap.
     *
     * @param str  - Key
     * @param data - Value
     */
    public void insertDataObject(String str, Data data)
    {
        data_list.put(str, data);
    }

    /**
     * Retrieves the value of the <key,value> pair
     *
     * @param str - Key
     * @return Data
     */
    public Data retrieveDataObject(String str)
    {
        return data_list.get(str.toUpperCase());
    }

    /**
     * Removes all objects from the HashMap.
     */
    public void clearObjectService()
    {
        data_list.clear();
    }

    /**
     * Removes the mapping from the HashMap matching the key.
     *
     * @param str - key
     * @return Data object being remove that is associated with the Key
     */
    public Data removeDataObject(String str)
    {
        return data_list.remove(str.toUpperCase());
    }

    /**
     * Returns the String representation of the ObjectCollectionService class
     */
    public String toString()
    {
        String temp = null;
        for (Object value : data_list.values())
        {
            temp += value + "\n";
        }
        return temp;
    }
}
