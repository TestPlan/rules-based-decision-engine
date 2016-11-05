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
    private static FileWriterService INSTANCE = null;

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
            bw.write(content);

            bw.write("\n\n"); // Newlines for writing additional rules
            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
