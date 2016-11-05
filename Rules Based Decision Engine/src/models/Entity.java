package models;

import java.util.HashMap;

/**
 * An Entity is a collection of Data objects.
 *
 * An Entity represents a "thing" like, for example, a submarine.
 * The submarine may have a temperature, latitude, and longitude.
 * The HashMap field <code>map</code> is a collection of the above datapoints as Data objects.
 *
 * @author Michael Crinite
 */
public class Entity
{
    private String name;
    private HashMap<String, Data> map;

    /**
     * Constructor for type Entity
     *
     * @param name The name of the thing this Entity object represents
     * @param map The map containing the Data for this object
     */
    public Entity(String name, HashMap<String, Data> map)
    {
        setName(name);
        setData(map);
    }

    /**
     * Finds the value of a Data object based on its key
     *
     * @param str The key in the hashmap associated with the object
     * @return The value of the associated Data object
     */
    public Object getValue(String str)
    {
        Data temp = get(str);
        return temp.getData();
    }

   /**
     * Replace the map of Data objects with a new map of Data objects
     * @param newMap The replacement map
     */
    public void replaceData(HashMap<String,Data> newMap)
    {
        map = newMap;
    }

    //TODO: Append maps

    /**
     * Replaces the entry in <code>map</code> for the specified key only if it is mapped to a value
     *
     * @param key The key of the object being replaced. This will also be the key of the new object
     * @param newData The Data object which will replace the old data at the specified key
     */
    public void updateData(String key, Data newData)
    {
        key = key.trim().toUpperCase();
        map.replace(key, newData);
    }

    /**
     * Updates the value of the field <code>value</code> in the Data object at the specified key
     * @param key The key of the object whose value will be updated
     * @param newValue The new value of the Data object
     */
    public void updateData(String key, Object newValue) {
        key = key.trim().toUpperCase();
        Data data = map.get(key);

        if (newValue instanceof Integer
            || newValue instanceof String
            || newValue instanceof Double
            || newValue instanceof Character
            || newValue instanceof Boolean) {
            data.setData(newValue);
        }
    }

    /**
     * Removes the Data object at the specified key from the map
     * @param key The key the Data is expected to be mapped to
     */
    public void removeData(String key)
    {
        key = key.trim().toUpperCase();
        map.remove(key);
    }

    /**
     * Gets the Data object mapped to the specified key, if it exists
     *
     * @param key The key of the object to get from the map
     * @return The object mapped to the specified key
     */
    public Data get(String key)
    {
        key = key.trim().toUpperCase();
        if(map.containsKey(key))
        {
            return map.get(key);
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the name of the Entity
     * @param name Name to set
     */
    public void setName(String name)
    {
        if(name != null)
        {
            this.name = name;
        }
        else
        {
            this.name = "Entity";
        }
    }

    /**
     * Associates a map of Data objects with the Entity
     * @param map A HashMap of Data objects
     */
    public void setData(HashMap<String,Data> map)
    {
        if(map != null)
        {
            this.map = map;
        }
        else
        {
            this.map = new HashMap<String,Data>();
        }
    }

    /**
     * Getter for field <code>name</code>
     * @return The name of the Entity
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter for field <code>map</code>
     * Allows the user to do work directly on the map if the
     * put, append, remove, and replace functionality in this Class
     * are not sufficient
     *
     * @return The map of Data for the entity
     */
    public HashMap<String,Data> getData()
    {
        return map;
    }
}
