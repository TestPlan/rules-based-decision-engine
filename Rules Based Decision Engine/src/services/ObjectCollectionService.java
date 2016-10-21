package services;
import java.util.HashMap;
import models.ObjectData;

/**
 * Static class designed to store all rule object data and any methods designed to manipulate the data.
 * @author Trae X. Lewis
 * @version 2.0 10/20/2016
 */
public class ObjectCollectionService {

	
	private static HashMap<String , ObjectData> data_list = new HashMap<String, ObjectData>();
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
	
	
	public void insertDataObject(String str, ObjectData data)
	{
		data_list.put(str, data);
	}
	
	public ObjectData retrieveRuleObject(String str)
	{		
		str.toUpperCase();
		return data_list.get(str);	
	}
	
	public void clearObjectService()
	{
		data_list.clear();
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
