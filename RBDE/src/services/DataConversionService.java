/**
 * Global Service
 * - Allows for the global use of DataConversionService. 
 * @author Trae X. Lewis
 *
 */
package services;

import models.ObjectType;
import models.RuleObjectData;

public class DataConversionService 
{
	// List of services 
	ObjectCollectionService obj_svc = ObjectCollectionService.getInstance();

	private ObjectType obj_type;
	private static DataConversionService INSTANCE = null;
	
	private DataConversionService(){}
	
	
	public static DataConversionService getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new DataConversionService();
		}
		return INSTANCE;
	}
	
	

	/**
	 * This method is designed to create a new RuleObjectData object based on is describe data type.
	 * @param name - Object identifying name.
	 * @param dataType - Data type of the value.
	 * @param value 
	 */
	public void objectAdder(String name , String dataType, String value)
	{
		RuleObjectData data = new RuleObjectData();

		// Checks dataType
		if(typeCheck(dataType))
		{
			switch(obj_type)
			{
			case STRING: 
				data.setName(name.toUpperCase());
				data.setType(dataType.toUpperCase());
				data.setData(value);
				break;
			case DOUBLE: 
				data.setName(name.toUpperCase());
				data.setType(dataType.toUpperCase());
				data.setData(Double.valueOf(value));
				break;
			case BOOLEAN: 
				data.setName(name.toUpperCase());
				data.setType(dataType.toUpperCase());
				data.setData(Boolean.valueOf(value));
				break;
			case CHAR:
				data.setName(name.toUpperCase());
				data.setType(dataType.toUpperCase());
				data.setData(Character.valueOf(charParse(value)));
				break;
			default:
				// Throws exception if not accepted data type.
				throw new IllegalArgumentException("DataConversionService::objectAdder() - String: " + name + " is NOT an accepted data type");
			}
			// Inserts rule object into ObjectCollectionService
			obj_svc.insertRuleObject(name, data);
		}	

	}

	/**
	 * This method checks input string for correct amount of characters to be considered a char data type.
	 * If str.length() != 1 , then str does not conform.
	 * @param str - argument to be transformed to char data type.
	 * @return char representation of string
	 */
	private char charParse(String str)
	{
		char ch;
		
		str.trim();
		if(str.length() != 1)
		{
			throw new IllegalArgumentException("DataConversionService::charParse - Input stream exceeds maximum length of char: [Input value: " + str + " ]");
		}
		
		ch = str.charAt(0);
		
		return ch; 
	}

	
	/**
	 * Checks if argument is a valid ObjectType.
	 * @param dataType
	 * @return true if dataType is a valid enumeration.
	 */
	private boolean typeCheck(String dataType)
	{
		obj_type = null;
		boolean type = false;
		
		dataType.toUpperCase();
		for(ObjectType obj : ObjectType.values())
		{
			if(obj.toString().equals(dataType))
			{
				type = true;
				obj_type = obj;
			}
		}
		return type;
	}
	
	
	
}
