package models;

/**
 * Class representation of an individual ObjectData object.
 * This class is responsible for the creation of data points and includes
 * both setters and getters for each instance variable along with constructors.
 * 
 * @author Trae X. Lewis
 * @version 2.0 10/20/2016
 */
public class ObjectData 
{

	String name;
	String type;
	Object value;
	
	/**
	 * Default constructor
	 */
	public ObjectData()
	{
		
	}

	
	/**
	 * Generic constructor. Creates ObjectData object
	 * @param name - Name of the object
	 * @param type - Data type associated with the objects value.
	 * @param object - Object representation of the data of object.
	 */
	public ObjectData(String name, String type, Object object)
	{
		this.name = name.toUpperCase();
		this.type = type;
		this.value = object;
	}
	
	/**
	 * Sets name attribute
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name.toUpperCase();
	}
	
	/**
	 * Returns name attribute
	 * @return
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Returns data type of attribute
	 * @return
	 */
	public String getType()
	{
		return this.type;
	}
	
	/**
	 * Sets data type attribute
	 * @param type
	 */
	public void setType(String type)
	{
		this.type = type;
	}
	
	/**
	 * Sets value of object
	 * @param object
	 */
	public void setData(Object object)
	{
		this.value = object;
	}
	
	/**
	 * Returns the value of object
	 * @return
	 */
	public Object getData()
	{
		return this.value;
	}
	
	/**
	 * Resets object parameters to null.
	 * For use when object is used as an instance variable. 
	 */
	public void clearData()
	{
		this.value = null;
		this.type = null;
		this.name = null;
	}
	
	/**
	 * String representation of ObjectData object.
	 */
	public String toString()
	{
		return this.name.toString() + ":\t\t" + this.value.toString() + "\n";
	}
	
	
}
