package models;

public class RuleObjectData {

	String name;
	String type;
	Object value;
	
	
	public RuleObjectData()
	{
		
	}

	public RuleObjectData(String name, String type, Object object)
	{
		this.name = name;
		this.type = type;
		this.value = object;
	}
	
	
	public void setName(String name)
	{
		this.name = name.toUpperCase();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setType(String type)
	{
		this.type = type.toUpperCase();
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public void setData(Object object)
	{
		this.value = object;
	}
	
	public Object getData()
	{
		return this.value;
	}
	
	public String toString()
	{
		return this.name + ": " + this.value.toString() + "\n";
	}
	
}
