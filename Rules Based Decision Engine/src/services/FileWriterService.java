package services;

import models.Rule;
import helpers.CreateDroolsFile;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Provides a service for utilizing the Rule.toString to write to .drl files.
 *
 * @author Michael Crinite
 */
public class FileWriterService
{
    //Fields
    private static FileWriterService INSTANCE = null;
    private static boolean importCheck = false;

    /**
     * Retrieves the instance of the FileWriterService.
     * <p>
     * Creates an instance if there is none.
     *
     * @return The class instance
     */
    public static FileWriterService getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new FileWriterService();
        }
        return INSTANCE;
    }

    /**
     *
     * Attempts to write to a file. Accepts the File object and the Rule object.
     * If a file does not exist in the specified location, it creates one there.
     *
     * @param file   The file to write to.
     * @param rule   The rule to write in the file.
     * @param append If <code>true</code>, the writer will begin writing at the end of the file, rather
     *               than at the beginning. If <code>false</code> the writer will overwrite the file.
     */
    public void writeToFile(File file, Rule rule, boolean append)
    {
        try
        {
            String content = rule.toString();

            // Create file if it doesn't exist
            if (!file.exists())
            {
                new CreateDroolsFile().makeDroolsFile(rule.getTitle());
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), append);
            BufferedWriter bw = new BufferedWriter(fw);

            if(!importCheck){
                bw.write(drlImportSetter());
            }
            bw.write(content);
            bw.write("\n\n"); // Newlines for writing additional rules
            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Adds the default header to each .drl file
     * @return A String containing the default header of a .drl file
     */
    public String drlImportSetter()
    {
        importCheck = true;
        String s = "import models.*;\n" +
            "import services.*;\n" +
            "dialect \"mvel\"\n\n";
        return s;
    }

    //TODO: Finish export rules method
/*    public void exportRulesMade()
    {
        PrintWriter outputStream = null;     //Writes the notes in notebook to the text file
        try
        {
            outputStream = new PrintWriter(fileLoc);
            for (int i = 0; i < rules.size(); i++)
            {
                outputStream.println(rules.get(i).getTitle() + "," + rules.get(i).getAction());
            }
            outputStream.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error opening file!");
            System.exit(0);
        }
    }*/
}
