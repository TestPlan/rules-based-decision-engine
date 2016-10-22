package helpers;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ian Markind on 10/9/2016.
 */
public class CreateDroolsFile
{

    private String filename;
    private final String BASE_NAME = "/src/rules/";

    public CreateDroolsFile(String filename)
    {
        this.filename = filename + ".drl";
    }

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

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public String getBASE_NAME()
    {
        return BASE_NAME;
    }
}
