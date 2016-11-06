package services;

import models.Rule;
import org.kie.internal.agent.conf.ScanDirectoriesOption;

import javax.swing.*;
import java.io.*;

/**
 * The purpose of this class is to give the user the ability
 * to pass and serialize/deserialize an object.
 * In order for this to be able to happen, the object and
 * everything implemented by that object must be serializable.
 *
 * @author Mike Moscariello
 */
public class SerializationService
{
    private String fileloc = null;
    private final String fileExt = ".ser";
    private boolean b = false;
    private static SerializationService instance = null;

    /**
     * Default constructor for the SerializationService class.
     */
    private SerializationService()
    {

    }

    /**
     * getInstance needed for the singleton pattern; checks to see if an instance of this
     * class has been created.  If not, it creates a new instance, if so, it returns the instance already made.
     * @return instance of SerializationService
     */
    public static SerializationService getInstance()
    {
        if (instance == null)
        {
            instance = new SerializationService();
        }
        return instance;
    }

    /**
     * This class is to take in an object and serialize it into a user specified .ser file.
     * All objects being serialized must implement the serializable interface,
     * or contain the "transient" access modifier in the class being serialized.
     * @param o - The object being serialized
     * @param filename
     */
    public void serialize(Object o, String filename)
    {
        this.b = true;
        chooseFileLocation();

        if(o instanceof Serializable)
        {
            try
            {
                FileOutputStream fileOut = new FileOutputStream(this.fileloc + filename + this.fileExt);
                ObjectOutputStream output = new ObjectOutputStream(fileOut);
                output.writeObject(o);
                output.close();
                fileOut.close();

            }
            catch (NotSerializableException s){
                System.err.println("One or more of your serialized classes do not contain serializable variables.");
                s.printStackTrace();
            }
            catch (IOException i)
            {
                i.printStackTrace();
            }
        }
    }

    /**
     * This class is to deserialize an object from a user specified .ser file into a newly created object that
     * is passed into the method.
     * @param o - The object being deserialized
     * @return Object
     */
    public Object deserialize(Object o)
    {
        this.b = false;
        chooseFileLocation();

        if (o instanceof Serializable)
        {
            try
            {
                FileInputStream fileInput = new FileInputStream(fileloc);
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                o = objectInput.readObject();
                objectInput.close();
                fileInput.close();
                //TODO Catch when the object being deserialized is different than the passed in object.
            }
            catch (IOException i)
            {
                i.printStackTrace();
            }
            catch (ClassNotFoundException c)
            {
                c.printStackTrace();
            }
        }
        return o;
    }

    /**
     * This method's purpose is to give the user access to their file system so that they can choose the file
     * they want to deserialize, or the directory they want their serialized file in.
     */
    public void chooseFileLocation(){
        JFileChooser fc = new JFileChooser(".");
        if (this.b == false)
        {
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fc.showOpenDialog(null);
        }
        else if (this.b == true)
        {
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.showOpenDialog(null);
        }
        this.fileloc = fc.getSelectedFile().getAbsolutePath() + "\\";
    }



}
