package controllers;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.io.KieResources;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import java.io.File;

/**
 * Created by Mike on 10/20/2016.
 * The purpose of this class is to receive the drl files the user wants to run data through,
 * and run that data through the file(s).
 */
public class FireRules {

    final private String filePath = "./src/rules/";
    //TODO: Similar fix as in CreateDroolsFile


    private KieServices kServices;
    private KieContainer kContainer;
    private KieSession kSession;
    private KieResources kResources;
    private KieFileSystem kfs;
    private KieRepository kRepo;

    public FireRules(String filename, Object obj)
    {
        this.kServices = KieServices.Factory.get();
        this.kResources = kServices.getResources();
        this.kfs = kServices.newKieFileSystem();
        this.kRepo = kServices.getRepository();

        addExistingFile(filename);
        buildKnowledgeSession(obj);
        fireAllRules();
        dispose();
    }

    public FireRules(String[] filenames)
    {
        this.kServices = KieServices.Factory.get();
        this.kResources = kServices.getResources();
        this.kfs = kServices.newKieFileSystem();
        this.kRepo = kServices.getRepository();


    }

    public void addExistingFile(String filename)
    {

        //kfs.write(filePath + filename, resource); //If I am interpreting this correctly,
                                                    // this method call creates a new file in the kResource filesystem:
                                                    // write(file location, what goes in the file).  Might only create an instance of the file?

        kfs.write(ResourceFactory.newFileResource(new File(filePath + filename)));  //This on the other hand is used to fire rules from
                                                                                    //existing drl files.
    }

    public KieSession buildKnowledgeSession(Object obj)
    {
        KieBuilder kb = kServices.newKieBuilder(kfs);
        kb.buildAll();

        if(kb.getResults().hasMessages(Message.Level.ERROR))
        {
            throw new RuntimeException("Build Errors: \n" + kb.getResults().toString());
        }

        kContainer = kServices.newKieContainer(kRepo.getDefaultReleaseId());

        kSession = this.kContainer.newKieSession();

        kSession.insert(obj);

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

}
