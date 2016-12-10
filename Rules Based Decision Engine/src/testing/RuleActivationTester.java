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

//        for (Map.Entry<String, Entity> e : entity_svc.entrySet()) {
//            Entity temp = e.getValue();
//            System.out.println(temp.toString());
//        }

        String s = "" +
            "package rules;\n" +
            "import models.*;\n" +
            "dialect \"mvel\"\n\n" +

            "rule \"temperature\"\n" +
            "when\n" +
            "   e1 : Entity(getValue(\"TRAESUB1.VALUE\") <= 50.0 )" +
            "then\n" +
            "   System.out.println(e1 + \" drl contents works!\");\n" +
            "end";

        Entity[] entities = entity_svc.getAllEntities();

        Entity[] entities2 = {entities[0], entities[2]};
        for (Entity e : entities)
        System.out.println(e.toString());

        RuleActivation ra = new RuleActivation(Driver.chooseFileLocation(),entities);
        //RuleActivation ra2 = new RuleActivation(s, entities);
    }
}
