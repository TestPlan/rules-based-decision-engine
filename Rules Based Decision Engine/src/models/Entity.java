package models;


import java.util.HashSet;
import java.util.Set;

import services.EntityCollectionService;
import services.ObjectCollectionService;

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
    private String entity_name;
    private Set<String> key_set = new HashSet<String>();
    ObjectCollectionService obj_svc = ObjectCollectionService.getInstance();
    EntityCollectionService entity_svc = EntityCollectionService.getInstance();

    /**
     * Default constructor for type Entity
     */
    public Entity()
    {
    	entity_name = entity_svc.defaultName();
    }
    
    public Entity(String entity_name)
    {
    	this.entity_name = entity_name;
    }
    
    public Entity(String entity_name, Set<String> set)
    {
    	this.entity_name = entity_name;
    	this.key_set = set;
    }


    /**
     * Finds the value of a Data object based on its key
     *
     * @param key The key in the hashmap associated with the object
     * @return The value of the associated Data object
     */
    public Object getValue(String key)
    {
    	if(this.key_set.contains(key))
    	{
    		return obj_svc.get(key);
    	}
    	return null;
    }

    /**
     *Adds all of the elements in the specified collection to this set if they're not already present (optional operation). 
     *The addAll operation effectively modifies this set so that its value is the union of the two sets.
     *
     * @param  c represents a collection of keys corresponding to a mapping inside ObjectCollectionService.
     * @return true if this set changed as a result of the call
     */
    public boolean insertKeySet(Set<String> keys)
    {
    	int count = 0;
    	
    	for(String key : keys)
    	{
    		if(obj_svc.containsKey(key))
    		{
    			key_set.add(key);
    			count++;
    		}
    	}
    	return (count != 0);
    }
    


    /**
	 * Adds the specified key to this set if it is not already present (optional operation).
	 *  If this set already contains the element, the call leaves the set unchanged and returns false. 
	 *  
	 * This function is designed to insert a single key corresponding to a Map<key,value> mapping in the ObjectCollectionService.
	 * The key is a reference to a key the ObjectCollectionService and therefore must exist in the OCS.
	 * 
	 * @param key key to be added to this set.
	 * @return true if this set did not already contain the specified element.
	 */
	public boolean insertKey(String key)
	{
		boolean result = false;
		
		if(obj_svc.containsKey(key))
			result = key_set.add(key);
		
		return result;
	}

	/**
     * Associates the specified value with the specified key in ObjectCollectionService data map. 
     * If the map previously contained a mapping for the key, the old value is replaced.
     * 
     * This method strictly updates the value mapped to the key IFF the key exists in Set<key> of instance.
     * 
     * NOTE: ObjectCollectionService data objects are shared collective across all Entity instances.
     * 		 Instance of Entity only contains a set of keys that reference the data objects in ObjectCollectionService.
     * 		 An update to the Map<String,Object> from one Entity effects all Entity instances.
     * @param key The key of the object being replaced. This will also be the key of the new object.
     * @param value The Object which will replace the previous data for the specified key.
     */
    public Object updateData(String key, Object value)
    {
    	Object temp = null;
    	
    	if(obj_svc.containsKey(key) && key_set.contains(key))
    	{
        	temp = obj_svc.put(key, value);
    	}

    	
    	return temp;
    }
    
    
    /**
     * Associates the specified value with the specified a generated key derived from the entity name and name argument.
     * Key and value pair is then inserted in ObjectCollectionService and key is added to the Entity key set.
     *  
     * Overrides any data associated with the key if it is discovered it is not a unique key.
     * @param name name describing the object. (Should not contain entity_name prefix)
     * @param value value to be associated with the specified key
     * @return true if this set did not already contain the specified element
     */
    //TODO: More error checking required.
    public boolean insertData(String name, Object value)
    {
        String key = entity_name + "." + name.toUpperCase().trim();
        
        obj_svc.put(key, value);

       return key_set.add(key);    

    }

    /**
     * Removes the specified element from this set if it is present (optional operation).
     *  
     * @param key key corresponding to map entry in ObjectCollectionService to be removed from Entity key set.
     * @return true if key set contained the specified key.
     */
    public boolean removeData(String key)
    {
    	return this.key_set.remove(key.trim().toUpperCase());
    }



    /**
     * Sets the name of the Entity
     * @param name Name to set
     */
    //TODO: requires a decent amout of work. Not so simple.
    //TODO: Decide whether user is capable of updating entity name.
    //TODO: Decide the scope of the update name change. It effects all data objects associated with entity.
    //TODO: Need to update the KeySet for every Entity in EntityCollectionService from the old name to new name.
    public void updateEntityName(String name)
    {
    	
    }



    /**
     * Getter for field <code>name</code>
     * @return The name of the Entity
     */
    public String getEntityName()
    {
        return this.entity_name;
    }



    /**
     * Formats info in Entity to a printable string.
     * @return Formatted string containing all data in Entity
     */
    public String toString()
    {
    	String result = "Entity Name:\t" + this.entity_name + "\n";
    	for(String key : key_set)
    	{
    		Object value = obj_svc.get(key);
    		result += key + ":\t" + value +"\n";
    	}
    	
    	return result;
    }
}
