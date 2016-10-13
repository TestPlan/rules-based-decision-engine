package services;

import java.util.HashMap;
import models.RuleObjectData;

/**
 * Static class designed to store all rule object data and any methods designed to manipulate the data.
 * @author Trae X. Lewis
 *
 */
public class ObjectCollectionService {

	
	private static HashMap<String , RuleObjectData> data_list = new HashMap<String, RuleObjectData>();
	private static ObjectCollectionService INSTANCE ;
	
	
	private ObjectCollectionService(){}
	
	
	public static ObjectCollectionService getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new ObjectCollectionService();

		}
		return INSTANCE;
	}
	
	
	public void insertRuleObject(String str, RuleObjectData data)
	{
		data_list.put(str, data);
	}
	
	public RuleObjectData retrieveRuleObject(String str)
	{		
		str.toUpperCase();
		return data_list.get(str);	
	}
	
	public String toString()
	{
		String temp = "";
		for (Object value : data_list.values())
		{
			temp += value;
		}
		return temp;
	}
}
