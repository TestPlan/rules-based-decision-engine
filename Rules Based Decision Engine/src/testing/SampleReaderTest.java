package testing;

import models.Entity;
import services.EntityCollectionService;
import services.FileReaderService;
import java.util.Map.Entry;

/**
 * Created by shiv on 11/12/2016.
 */
public class SampleReaderTest
{

    public static void main(String args[])
    {
       FileReaderService fr = FileReaderService.getInstance();
        EntityCollectionService entity_svc = EntityCollectionService.getInstance();
        fr.readJsonFile(System.getenv("RBDE_HOME")+"temp.json");
  //    fr.readJsonFile("./res/data/temp.json");

        for(Entry<String, Entity> e : entity_svc.entrySet())
        {
        	Entity temp = e.getValue();
        	System.out.println(temp.toString());
        }
    }

}
