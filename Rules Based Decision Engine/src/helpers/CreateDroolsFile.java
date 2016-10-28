package helpers;

import java.io.File;
import java.io.IOException;

//TODO: Should really be a service. If you create 1000 .drl files do you really want 1000 instances of the same class when they all can use 1 class.
/**
 * Created by Ian Markind on 10/9/2016.
 */
public class CreateDroolsFile
{

    private String filename;
    //TODO: Should NOT be hard coded
    private final String BASE_NAME = "/src/rules/";

    public CreateDroolsFile(String filename)
    {
        this.filename = filename + ".drl";
    }

    //TODO: this method should pass in the filename parameter instead of constructor. 
    public void makeDroolsFile()
    {

        final String WORKSPACE = new File("").getAbsolutePath();
        final String PATH = WORKSPACE + BASE_NAME;

        try
        {
            File file = new File(PATH, filename);

            if (file.createNewFile())
            {
                System.out.println("Drools file created.");
            }
            else
            {
                System.err.println("Drools file: " + filename + " already exists.");
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getFilename()
    {
        return filename;
    }

    //TODO: When a new filename is given there should be an update method that actually changes the name of the file instead of setting instance variable only.
    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    //TODO: Method naming convention bad.
    public String getBASE_NAME()
    {
        return BASE_NAME;
    }
}
