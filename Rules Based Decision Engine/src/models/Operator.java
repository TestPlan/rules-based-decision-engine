package models;

/**
 * Enumeration class for supported operators.
 * 
 * @author Trae X. Lewis
 * @version 1.0
 */
public enum Operator 
{
	EQUAL_TO ("=="),
	GREATER_THAN (">"),
	LESS_THAN ("<"),
	GREATER_EQUAL(">="),
	LESS_EQUAL("<="),
	NOT_EQUAL("!=");
	
	private String value;
	
	private Operator(String value)
	{
		this.value = value;
	}
	
	public String toString()
	{
		return value;
	}
	
}
