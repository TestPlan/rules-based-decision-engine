package models;

import java.io.Serializable;

/**
 * Class representation of an individual Data object.
 * This class is responsible for the creation of data points and includes
 * both setters and getters for each instance variable along with constructors.
 *
 * @author Trae X. Lewis
 * @version 2.0 10/20/2016
 */
public class Data implements Serializable
{

	String name;
	String type;
	Object value;

	/**
	 * Default constructor
	 */
	public Data()
	{

	}


	/**
	 * Generic constructor. Creates Data object
	 * @param name - Name of the object
	 * @param type - Data type associated with the objects value.
	 * @param object - Object representation of the data of object.
	 */
	//TODO: type SHALL be an ObjectType enum, NOT String
	public Data(String name, String type, Object object)
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
	 * String representation of Data object.
	 */
	public String toString()
	{
		return this.name.toString();
	}



}
