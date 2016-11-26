package helpers;

import java.io.File;
import java.io.IOException;

// TODO: Should really be a service. If you create 1000 .drl files do you really want 1000 instances of the same class when they all can use 1 class.

/**
 * Created by Ian Markind on 10/9/2016.
 */
public class CreateDroolsFile
{

    private String filename;

    private String path = "./src/rules/";

    public CreateDroolsFile()
    {

    }

    /**
     * Creates and returns a File with the given file name
     */
    public File makeDroolsFile(String filename)
    {
        setFilename(filename + ".drl");
        File file = null; // TODO: error handling for this null value

        try
        {
            file = new File(path, getFilename());

            if (file.createNewFile())
            {
                System.out.println("Drools file created.\n");
            }
            else
            {
                System.err.println("Drools file: " + this.filename + " already exists.\n");
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return file;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }
}
