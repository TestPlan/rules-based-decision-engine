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
        //fr.readJsonFile(Driver.chooseFileLocation());
        //    fr.readJsonFile("./res/data/temp.json");

//        for (Map.Entry<String, Entity> e : entity_svc.entrySet()) {
//            Entity temp = e.getValue();
//            System.out.println(temp.toString());
//        }

        String s =
            "rule \"avoid3\"\n" +
            "salience 2\n" +
            "when\n" +
            "    Entity (getValue( \"TRAESUB1.VALUE\") < 50  )\n" +
            "\n" +
            "then\n" +
            "    System.out.println(\"TooCOL\");\n" +
            "\n" +
            "\n" +
            "end\n" +
            "\n" +
            "rule \"president_order\"\n" +
            "salience -1\n" +
            "when\n" +
            "    Entity (getValue( \"PRESIDENT.Order\") == true  )\n" +
            "\n" +
            "then\n" +
            "    System.out.println(\"ATTACK!\");\n" +
            "\n" +
            "end\n" +
            "\n" +
            "rule \"is_jet_order\"\n" +
            "salience 0\n" +
            "when\n" +
            "    Entity (getValue( \"FIGHTERJET.ORDER\") == true  )\n" +
            "\n" +
            "then\n" +
            "    Entity e = EntityCollectionService.getInstance().getFromActions(\"PRESIDENT\");\n" +
            "    System.out.println(e);\n" +
            "    insert(e);\n" +
            "\n" +
            "\n" +
            "end\n";

        Entity[] entities = entity_svc.getAllEntities();

        //Entity[] entities2 = {entities[0], entities[2]};
        //for (Entity e : entities)
        //System.out.println(e.toString());

        RuleActivation ra = new RuleActivation(Driver.chooseFileLocation(),entities);
        //RuleActivation ra2 = new RuleActivation(s, entities);
    }
}
