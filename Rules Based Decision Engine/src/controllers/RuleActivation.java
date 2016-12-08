package controllers;


import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import java.io.File;
import java.util.regex.Pattern;

/**
 * The purpose of this class is to either receive the drl file(s) the user wants to run data through or
 * create the file from scratch using Rule object data and strings.
 * It then has the ability to run that data through the file(s), and create actions for the user.
 *
 * @author Mike Moscariello
 */
public class RuleActivation
{
    final private String regularExpressionPC = "([a-zA-Z]:)?(\\\\[a-zA-Z0-9 _.-]+)+\\\\?";
    final private String regularExpressionMac = "(/[a-zA-Z0-9 _.-]+)+/?";
    private Pattern pPC = Pattern.compile(regularExpressionPC);
    private Pattern pMac = Pattern.compile(regularExpressionMac);
    final private String kfsFilePath = "src/main/resources/org/kie/example/newRule.drl"; //path for the KieFileSystem to create a drl from a string
    //Must begin with src/main/resources/org

    //Below are components necessary for the activation of rules to work
    private KieServices kServices;      //Necessary to build the KieRepository, KieFileSystem, KieBuilder and KieContainer
    private KieContainer kContainer;    //Necessary to build the KieSession
    private KieSession kSession;        //Necessary to insert user data into the KieFileSystem, fire all written rules, and dispose after rules have been fired.
    private KieFileSystem kfs;          //Necessary for the creation of the virtual file system used to write and run rules.
    private KieRepository kRepo;        //

    /**
     * This constructor creates the necessary components to activate the rules
     * from one drl file (retrieved from the passed in filename) and runs the passed in object with them.
     * @param ruleInfo Can either be the filePath for a drl file or the contents of a drl file
     * @param obj Data from the user to pass into the drl files
     */
    public RuleActivation(String ruleInfo, Object obj)
    {
        this.kServices = KieServices.Factory.get();
        this.kRepo = kServices.getRepository();
        this.kfs = kServices.newKieFileSystem();

        //Below states whether ruleInfo is in the format of a filepath or not.
        if(Pattern.matches(regularExpressionPC, ruleInfo) || Pattern.matches(regularExpressionMac, ruleInfo)){
            //At this point, ruleInfo was found to be and will be used as a filepath
            addExistingFile(ruleInfo);
            buildKnowledgeSession(obj);
            fireAllRules();
            dispose();
        }
        else{
            //At this point, ruleInfo was not in the filepath format, so it is assumed to be drl contents
            addNonExistingFile(ruleInfo);
            buildKnowledgeSession(obj);
            fireAllRules();
            dispose();
        }
    }

    /**
     * This constructor creates the necessary components to activate the rules
     * from one drl file (retrieved from the passed in filename) and runs the passed in data with them.
     * @param localFilePath File name input for the KieFileSystem to use in rule activation
     * @param obj Data from the user to pass into the drl files
     */
//    public RuleActivation(String localFilePath, Object obj)
//    {
//        this.localFilePath = localFilePath;
//        this.kServices = KieServices.Factory.get();
//        this.kRepo = kServices.getRepository();
//        this.kfs = kServices.newKieFileSystem();
//
//        addExistingFile(localFilePath);
//        buildKnowledgeSession(obj);
//        fireAllRules();
//        dispose();
//    }

    /**
     * This constructor creates the necessary components to activate the rules
     * from multiple drl files (retrieved from the passed in filenames) and runs the passed in data with them.
     * @param filePaths File names inputted for the KieFileSystem to use in rule activation.
     * @param obj Data from the user to pass into the drl file.
     */
//    public RuleActivation(String[] filePaths, Object obj)
//    {
//
//        this.kServices = KieServices.Factory.get();
//        this.kfs = kServices.newKieFileSystem();
//        this.kRepo = kServices.getRepository();
//
//        for (String filename : filePaths)
//        {
//            addExistingFile(filename);
//        }
//        buildKnowledgeSession(obj);
//        fireAllRules();
//        dispose();
//    }

    /**
     * This constructor creates the necessary components to activate the rules
     * from a string-created drl file (not existing in local file system)
     * and runs the passed in data with them.
     * @param obj Data from the user to pass into the drl file.
     */
//    public RuleActivation(Object obj)
//    {
//        this.kServices = KieServices.Factory.get();
//        this.kfs = kServices.newKieFileSystem();
//        this.kRepo = kServices.getRepository();
//
//        addNonExistingFile();
//        buildKnowledgeSession(obj);
//        fireAllRules();
//        dispose();
//    }
//
//    public RuleActivation(String localFilePath, Object[] dataList)
//    {
//        this.kServices = KieServices.Factory.get();
//        this.kfs = kServices.newKieFileSystem();
//        this.kRepo = kServices.getRepository();
//
//        addExistingFile(localFilePath);
//        buildKnowledgeSession(dataList);
//        fireAllRules();
//        dispose();
//    }

    /**
     * This method takes a drl file from a computer's local file system
     * and writes it to the KieFileSystem, which is a virtual file system used to read the drl file.
     * @param localFilePath The file path inputted for the KieFileSystem to use in rule activation.
     */
    public void addExistingFile(String localFilePath)
    {
        kfs.write(ResourceFactory.newFileResource(new File(localFilePath)));  //This used to fire rules from
        //existing drl files.
    }

    /**
     * This method writes a drl file not existing in a user's local file system to the KieFileSystem,
     * which is a virtual file system used to read the drl file.
     */
    public void addNonExistingFile(String ruleContents)
    {
        kfs.write(kfsFilePath, ruleContents); //kfsFilePath is the file path necessary to use the KieFileSystem.
        //getRule() is a string that contains the exact contents of the drl for the kfs to read.
    }

    /**
     * This method's job is to create the necessary components in order to create a kSession.
     * In turn, the kSession is used to receive the data inputted from the user and insert it for use
     * in the kieFileSystem so it can be associated with drl files.
     * @param obj Data from the user to pass into the drl file.
     */
    public void buildKnowledgeSession(Object obj)
    {
        KieBuilder kb = kServices.newKieBuilder(kfs);
        kb.buildAll();

        if (kb.getResults().hasMessages(Message.Level.ERROR))
        {
            throw new RuntimeException("Build Errors: \n" + kb.getResults().toString());
        }

        kContainer = kServices.newKieContainer(kRepo.getDefaultReleaseId());

        kSession = this.kContainer.newKieSession();

        kSession.insert(obj);

    }

    /**
     * Overloaded method in case a user needs to insert multiple data objects into a drl file
     * @param objectList
     */
    public void buildKnowledgeSession(Object[] objectList)
    {
        KieBuilder kb = kServices.newKieBuilder(kfs);
        kb.buildAll();

        if (kb.getResults().hasMessages(Message.Level.ERROR))
        {
            throw new RuntimeException("Build Errors: \n" + kb.getResults().toString());
        }

        kContainer = kServices.newKieContainer(kRepo.getDefaultReleaseId());

        kSession = this.kContainer.newKieSession();

        for(Object obj : objectList)
        {
            kSession.insert(obj);
        }

    }

    /**
     * This method takes the the drl files previously writted and activates them,
     * therefore associating the data previously inserted with the rules.
     */
    public void fireAllRules()
    {
        kSession.fireAllRules();
    }

    /**
     *  Remove all the references that have the knowledge session to the domain objects
     *  and internal objects that are not needed anymore.
     */
    public void dispose()
    {
        this.kSession.dispose();
    }


}
