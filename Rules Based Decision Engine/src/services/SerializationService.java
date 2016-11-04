package services;

import models.Rule;
import org.kie.internal.agent.conf.ScanDirectoriesOption;

import javax.swing.*;
import java.io.*;

/**
 * Created by Mike on 11/3/2016.
 */
public class SerializationService
{
    private String fileloc = null;
    private final String fileExt = ".ser";
    private boolean b = false;
    private static SerializationService instance = new SerializationService();

    private SerializationService()
    {

    }

    public static SerializationService getInstance()
    {
        return instance;
    }

    public void serialize(Object o, String filename)
    {
        this.b = true;
        this.chooseFileLocation();

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

    public Object deserialize(Object o)
    {
        //System.out.println(o.getClass());
        this.b = false;
        this.chooseFileLocation();

        if (o instanceof Serializable)
        {
            try
            {
                FileInputStream fileInput = new FileInputStream(fileloc);
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                o = objectInput.readObject();
                objectInput.close();
                fileInput.close();
                System.out.println(this.fileloc);
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

    public void chooseFileLocation(){
        JFileChooser fc = new JFileChooser();
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
        System.out.println(this.fileloc);
    }



}
