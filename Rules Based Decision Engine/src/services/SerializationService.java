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
    private String fileloc = new File("").getAbsolutePath() + "/src/serialized/";
    private final String fileExt = ".ser";
    private static SerializationService instance = new SerializationService();

    private SerializationService()
    {

    }

    public static SerializationService getInstance()
    {
        return instance;
    }

    public boolean serialize(Object o, String filename)
    {
        boolean b = false;
        if(o instanceof Serializable)
        {
            try
            {
                FileOutputStream fileOut = new FileOutputStream(this.fileloc + filename + fileExt);
                ObjectOutputStream output = new ObjectOutputStream(fileOut);
                output.writeObject(o);
                output.close();
                fileOut.close();

                b = true;
            }
            catch (IOException i)
            {
                i.printStackTrace();
            }
        }
        return b;
    }

    public Object deserialize(Object o, String filename)
    {
        System.out.println(o.getClass());
        boolean b = false;

        if (o instanceof Serializable)
        {
            try
            {
                FileInputStream fileInput = new FileInputStream(fileloc + filename + fileExt);
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                o = objectInput.readObject();
                objectInput.close();
                fileInput.close();
                b = true;
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
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //int returnVal = fc.showOpenDialog(JFileChooser.DIRECTORIES_ONLY);
        fc.showOpenDialog(null);
        this.fileloc = fc.getSelectedFile().getAbsolutePath();
        System.out.println(fileloc);
    }



}
