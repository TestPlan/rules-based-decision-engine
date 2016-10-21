package models;

/**
 * Enumeration class for supported data types. 
 * 
 * @author Trae X. Lewis
 * @version 1.0 10/20/2016
 */
public enum ObjectType {
	
	INT ("INT"),
	STRING  ("STRING"), 
	DOUBLE ("DOUBLE"), 
	CHAR    ("CHAR"),
	BOOL ("BOOL") ;
	
	private String value;
	
	/**
	 * Private constructor for ObjectType.
	 * @param value
	 */
	private ObjectType(String value)
	{
		this.value = value;
	}
	
	/**
	 * Returns the value of a enumeration.
	 * @return
	 */
	public String getValue()
	{
		return this.value;
	}


}
