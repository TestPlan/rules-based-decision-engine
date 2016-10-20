package controllers;

import models.ObjectData;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.io.KieResources;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by Mike on 10/20/2016.
 * The purpose of this class is to receive the drl files the user wants to run data through,
 * and run that data through the file(s).
 */
public class FireRules {

    final private String filePath = "./src/rules/";

    private KieServices kServices;
    private KieContainer kContainer;
    private KieSession kSession;
    private KieResources kResources;
    private KieFileSystem kfs;
    private KieRepository kRepo;

    public FireRules(String filename, ObjectData objectData)
    {
        this.kServices = KieServices.Factory.get();
        this.kResources = kServices.getResources();
        this.kfs = kServices.newKieFileSystem();
        this.kRepo = kServices.getRepository();

        addFile(filename);
        buildKnowledgeSession(objectData);
        fireAllRules();
        dispose();
    }

    public FireRules(String[] filenames){
        this.kServices = KieServices.Factory.get();
        this.kResources = kServices.getResources();
        this.kfs = kServices.newKieFileSystem();
        this.kRepo = kServices.getRepository();


    }

    public void addFile(String filename)
    {
        Resource resource = kResources.newClassPathResource(filePath + filename);
        resource.setResourceType(ResourceType.DRL);
        //kfs.write(resourcepath, resource);
        kfs.write(resource);
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

}
