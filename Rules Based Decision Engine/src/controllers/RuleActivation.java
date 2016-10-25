package controllers;

import models.ObjectData;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import java.io.File;

/**
 * The purpose of this class is to receive the drl files the user wants to run data through,
 * and run that data through the file(s).
 * @author Mike Moscariello
 */
public class RuleActivation {

    final private String localFilePath = "./src/rules/";
    final private String kfsFilePath = "src/main/resources/org/kie/example/newRule.drl";

    private KieServices kServices;
    private KieContainer kContainer;
    private KieSession kSession;
    private KieFileSystem kfs;
    private KieRepository kRepo;

    public RuleActivation(String filename, ObjectData objectData)
    {
        this.kServices = KieServices.Factory.get();
        this.kRepo = kServices.getRepository();
        this.kfs = kServices.newKieFileSystem();

        addExistingFile(filename);
        buildKnowledgeSession(objectData);
        fireAllRules();
        dispose();
    }

    public RuleActivation(String[] filenames, ObjectData objectData){

        this.kServices = KieServices.Factory.get();
        this.kfs = kServices.newKieFileSystem();
        this.kRepo = kServices.getRepository();

        for (String filename : filenames) {
            addExistingFile(filename);
        }
        buildKnowledgeSession(objectData);
        fireAllRules();
        dispose();
    }

    public RuleActivation(ObjectData objectData){
        this.kServices = KieServices.Factory.get();
        this.kfs = kServices.newKieFileSystem();
        this.kRepo = kServices.getRepository();

        addNonExistingFile();
        buildKnowledgeSession(objectData);
        fireAllRules();
        dispose();
    }

    public void addExistingFile(String filename)
    {
       kfs.write(ResourceFactory.newFileResource(new File(localFilePath + filename)));  //This used to fire rules from
                                                                                        //existing drl files.
    }

    public void addNonExistingFile ()
    {
        kfs.write(kfsFilePath, getRule());
    }

    public KieSession buildKnowledgeSession(ObjectData objectData)
    {
        KieBuilder kb = kServices.newKieBuilder(kfs);
        kb.buildAll();

        if(kb.getResults().hasMessages(Message.Level.ERROR))
        {
            throw new RuntimeException("Build Errors: \n" + kb.getResults().toString());
        }

        kContainer = kServices.newKieContainer(kRepo.getDefaultReleaseId());

        kSession = this.kContainer.newKieSession();

        kSession.insert(objectData);

        return kSession;
    }

    public KieSession fireAllRules()
    {
        kSession.fireAllRules();
        return kSession;
    }

    public void dispose()
    {
        this.kSession.dispose();
    }

    public String getRule()
    {
        String s = "" +
            "package rules;\n" +
            "import models.Action;\n" +
            "import models.ObjectData;" +
            "dialect \"mvel\"\n\n" +

            "rule \"avoid\"\n" +
            "   when\n" +
                    "d : ObjectData( d.getData() <= 50.0 )" +
            "   then\n" +
            "       System.out.println(new Action(\"Hello\"))\n" + //d.getName() + \" too cold; Move away!\");\n" +
            "end";

        return s;
    }

}
