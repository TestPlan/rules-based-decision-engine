package unit_test;

import static org.junit.Assert.*;

import models.Data;
import org.junit.Before;
import org.junit.Test;


public class ObjectCollectionServiceTester
{

	Data data1 = new Data("TEMPERATURE", "DOUBLE", 98.6);
	Data data2 = new Data("NAME" , "STRING" , "Mathew");
	Data data3 = new Data("IS_HUNGRY" , "BOOLEAN" , true);

	services.ObjectCollectionService obj_svc = services.ObjectCollectionService.getInstance();



	@SuppressWarnings("restriction")
	@Before
	public void setUp()
	{
		obj_svc.insertDataObject(data1.getName(), data1);
		obj_svc.insertDataObject(data2.getName(), data2);
		obj_svc.insertDataObject("IS_HUNGRY", data3);
	}



	@SuppressWarnings("restriction")
	@Test
	public void testRetrieveRuleObject()
	{
		assertEquals(obj_svc.retrieveDataObject(data1.getName()),data1);
		assertEquals(obj_svc.retrieveDataObject("RANGE"), null);
		assertEquals(obj_svc.retrieveDataObject("name"), data2);
	}


	@SuppressWarnings("restriction")
	@Test
	public void testClearObjectService()
	{
		obj_svc.clearObjectService();

		assertEquals(obj_svc.toString(), null);
	}



}
