package services;

import models.Action;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Created by shiv on 10/14/2016.
 *
 * @author Shiv ,
 * @author Trae X. Lewis
 * @version 2.0 11/24/16
 */

public class ActionCollectionService implements Collectable<Action>, Serializable
{
    //Fields and services
    private HashMap<String, Action> actions = new HashMap<String, Action>();
    private HashMap<String, Action> redundant_list = new HashMap<String, Action>();

    private static final long serialVersionUID = 123456789L;
    private static ActionCollectionService Instance;


    /**
     * Retrieves the single instance of ActionCollectionService or creates it if none exist
     * @return The single instance of ActionCollectionService
     */
    public static ActionCollectionService getInstance()
    {
        if (Instance == null)
        {
            Instance = new ActionCollectionService();
        }
        return Instance;
    }

    /**
	 * CAUTION:
	 * Removes all of the mappings from this map. The map will be empty after this call returns.
	 *
	 * As a precaution, Map<key,value> is backed up to a redundant storage list.
	 * Redundant storage is cleared on the proceeding clear() method call which will effectively destroy
	 * any past data.
	 */
	public void clear()
    {
		redundant_list.clear();
		actions = redundant_list;
		actions.clear();
	}

	/**
	 * Returns true if this map contains an Action mapping for the specified key.
	 * @param key: key whose presence in this map is to be tested.
	 * @return true if this map contains a mapping for the specified key.
	 */
	public boolean containsKey(String key)
	{
	    return actions.containsKey(key.toUpperCase());
	}

	/**
	 * Returns true if this map maps one or more keys to the specified value.
	 * @param value: value whose presence in this map is to be tested.
	 * @return true if this map maps one or more keys to the specified value.
	 */
	public boolean containsValue(Action value)
	{
	    return actions.containsValue(value);
	}

	/**
	 * Returns a Set view of the mappings contained in this map.
	 * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa.
	 *
	 * @return a set view of the mappings contained in this map.
	 */
	public Set<Entry<String, Action>> entrySet()
	{
	    return actions.entrySet();
	}

	/**
	 * Returns the Action Object to which the specified key is mapped, or null if this map contains no mapping for the key.
	 *
	 * @param key key the key whose associated value is to be returned.
	 * @return Action matching key if found, else returns null.
	 */
	public Action get(String key)
	{
	    return actions.get(key);
	}

    /**
     * Return the entire list of actions
     * @return Map containing all actions
     */
	public String[] getActions()
    {
        String[] actionArr = new String[actions.size()];
        int index = 0;

        for(String s : actions.keySet()){
            actionArr[index] = s;
            index++;
        }

        return actionArr;
    }

	/**
     * Associates the specified value with the specified key in this Map<str,Action>.
     * If the map previously contained a mapping for the key, the old value is replaced by the specified value.
     * @param key: key with which the specified value is to be associated
     * @param value: value to be associated with the specified key.
     */
    public Action put(String key, Action value)
    {
       return actions.put(key.trim().toUpperCase(), value);
    }

    /**
     * Copies all of the mappings from the specified map to this map.
     * These mappings will replace any mappings that this map had for any of the
     * keys currently in the specified map.
     *
     * @param map mappings to be stored in this map
     */
    public void putAll(HashMap<String,Action> map)
    {
    	actions.putAll(map);
    }


    /**
     * Removes the Action mapping for a key from this map if it is present.
     * Null key not permitted.
     * @param key: key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no mapping for key.
     */
    public Action remove(String key)
    {
       return actions.remove(key.toUpperCase());
    }

    /**
     * Returns a string representation of the object.
     * In general, the toString method returns a string that "textually represents" this object.
     * @return a string representation of the object.
     */
    public String toString()
    {
        String temp = "";

        for (String key : actions.keySet())
        {
            temp += "\nAction Key: " + key.toString() + "\t\t" + actions.get(key).toString();
        }
        return temp;
    }


}
