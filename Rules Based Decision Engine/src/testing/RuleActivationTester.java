package testing;

import controllers.Driver;
import controllers.RuleActivation;
import models.Entity;
import services.EntityCollectionService;
import services.FileReaderService;

import java.util.Map;

/**
 * Created by Mello Mike on 12/3/2016.
 */
public class RuleActivationTester {

    public static void main(String args[]) {
        FileReaderService fr = FileReaderService.getInstance();
        EntityCollectionService entity_svc = EntityCollectionService.getInstance();
        fr.readJsonFile(Driver.chooseFileLocation());
        //    fr.readJsonFile("./res/data/temp.json");

        for (Map.Entry<String, Entity> e : entity_svc.entrySet()) {
            Entity temp = e.getValue();
            System.out.println(temp.toString());
        }

        Entity[] entities = {entity_svc.get("TRAESUB1"), entity_svc.get("TRAESUB2")};

        RuleActivation ra = new RuleActivation(Driver.chooseFileLocation(),entities);
    }
}
