package services;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;


/**
 * Static class designed to store all rule object data and any methods designed to manipulate the data.
 *
 * @author Trae X. Lewis
 * @author Wolf Team
 * @version 2.0 10/20/2016
 */
public class ObjectCollectionService implements Collectable<Object>, Serializable
{
    //Fields
    private static ObjectCollectionService INSTANCE;
    private static final long serialversionUID = 123456789L;

    private HashMap<String, Object> data_list = new HashMap<String, Object>();      // Main collection
    private HashMap<String, Object> redundant_list = new HashMap<String, Object>(); // Redundant collection
    private HashMap<String, Object> fromActions = new HashMap<String, Object>();    // Map of Objects that may be created
                                                                       // As the result of Rules firing

    /**
     * Default constructor for objects of type ObjectCollectionService
     */
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
		data_list = redundant_list;
	    data_list.clear();
	}


	/**
	 * Returns true if this map contains an Action mapping for the specified key.
	 * @param key: key whose presence in this map is to be tested.
	 * @return true if this map contains a mapping for the specified key.
	 */
	public boolean containsKey(String key)
	{
	    return data_list.containsKey(key.toUpperCase());
	}


	/**
	 * Returns true if this map maps one or more keys to the specified value.
	 * @param value: value whose presence in this map is to be tested.
	 * @return true if this map maps one or more keys to the specified value.
	 */
	public boolean containsValue(Object value)
	{
	    return data_list.containsValue(value);
	}

	/**
	 * Returns a Set view of the mappings contained in this map.
	 * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa.
	 *
	 * @return a set view of the mappings contained in this map.
	 */
	public Set<Entry<String, Object>> entrySet()
	{
	    return data_list.entrySet();
	}


	/**
	 * Returns the value to which the specified key is mapped,
	 * or null if this map contains no mapping for the key.
	 *
	 * @param key the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
	 */
	public Object get(String key)
	{
	    return data_list.get(key.toUpperCase());
	}

    /**
     * Returns an Object from the collection of Objects which may be created as the result of other Rules firing
     * or null if this map contains no mapping for the key
     *
     * @param key Key whose associated value is to be returned
     * @return The value to which the specified key is mapped
     */
    public Object getFromActions(String key)
    {
        return fromActions.get(key.toUpperCase());
    }

	/**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    public Object put(String key, Object value)
    {
       return data_list.put(key.trim().toUpperCase(), value);
    }

    /**
     * Associates the specified value with the specified key in the map of Objects which may be created
     * as the result of Rules firing
     *
     * @param key Key which should be associated with the specified value
     * @param value Value to associate with the specified key
     * @return <code>true</code> if the object was successfully added to the collection
     */
    public Object putFromActions(String key, Object value)
    {
        return fromActions.put(key.trim().toUpperCase(), value);
    }

    /**
     * Copies all of the mappings from the specified map to this map.
     * These mappings will replace any mappings that this map had for any of the
     * keys currently in the specified map.
     *
     * @param map mappings to be stored in this map
     */
    public void putAll(HashMap<String,Object> map)
    {
    	data_list.putAll(map);
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no mapping for key. (A null return can also indicate that the map previously associated null with key.)
     */
    public Object remove(String key)
    {
        return data_list.remove(key.toUpperCase());
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
