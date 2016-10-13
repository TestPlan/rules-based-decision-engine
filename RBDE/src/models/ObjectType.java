package models;

public enum ObjectType {
	
	STRING  ("STRING"), 
	DOUBLE ("DOUBLE"), 
	BOOLEAN ("BOOLEAN"), 
	CHAR    ("CHAR"), ;
	
	@SuppressWarnings("unused")
	private String value;
	
	ObjectType(String value)
	{
		this.value = value;
	}

}
