package services;

import models.Rule;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;


/**
 * Created by Mike on 10/9/2016.
 * Modified 11/24/16 (Trae X. Lewis)
 */
public class RuleCollectionService implements Collectable<Rule>
{

    private static RuleCollectionService INSTANCE = null;
    private HashMap<String, Rule> rules = new HashMap<String, Rule>();
    private HashMap<String, Rule> redundant_list = new HashMap<String, Rule>();



    public static RuleCollectionService getInstance()
    {
        if (INSTANCE == null)
        {
        	INSTANCE = new RuleCollectionService();
        }
        return INSTANCE;
    }

    private RuleCollectionService()
    {
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
		this.rules = redundant_list;
	    this.rules.clear();
	}

	/**
	 * Returns true if this map contains an Action mapping for the specified key.
	 * @param key: key whose presence in this map is to be tested.
	 * @return true if this map contains a mapping for the specified key.
	 */
	public boolean containsKey(String key)
	{
	    return rules.containsKey(key);
	}


	/**
	 * Returns true if this map maps one or more keys to the specified value.
	 * @param value: value whose presence in this map is to be tested.
	 * @return true if this map maps one or more keys to the specified value.
	 */
	public boolean containsValue(Rule value)
	{
	    return rules.containsValue(value);
	}

	/**
	 * Returns a Set view of the mappings contained in this map.
	 * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa.
	 *
	 * @return a set view of the mappings contained in this map.
	 */
	public Set<Entry<String, Rule>> entrySet()
	{
	    return this.rules.entrySet();
	}

	/**
	 * Returns the value to which the specified key is mapped,
	 * or null if this map contains no mapping for the key.
	 *
	 * @param key the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
	 */
	public Rule get(String key)
	{
	    return this.rules.get(key);
	}

	/**
     * Associates the specified value with the specified key in this map (optional operation).
     * If the map previously contained a mapping for the key, the old value is replaced by the specified value.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no mapping for key.
     *         (A null return can also indicate that the map previously associated null with key,
     *         if the implementation supports null values.)
     */
    public Rule put(String key, Rule value)
    {
       return this.rules.put(key, value);
    }


    /**
     * Copies all of the mappings from the specified map to this map.
     * These mappings will replace any mappings that this map had for any of the
     * keys currently in the specified map.
     *
     * @param map mappings to be stored in this map
     */
    public void putAll(HashMap<String,Rule> map)
    {
    	rules.putAll(map);
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no mapping for key. (A null return can also indicate that the map previously associated null with key.)
     */
    public Rule remove(String key)
    {
        return rules.remove(key);
    }


    public String toString()
    {
        String s = new String();

        for (HashMap.Entry<String, Rule> rule : this.rules.entrySet())
        {
            s += "Rule: " + rule.getKey() + "\n";
        }

        return s;
    }

    public Object[] toArray()
    {
        Object[] arr = rules.keySet().toArray();
        return arr;
    }



}
