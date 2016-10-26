package services;

import models.Rule;
import helpers.CreateDroolsFile;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by michaelcrinite on 10/26/16.
 */
public class FileWriterService
{
    private static FileWriterService INSTANCE = null;

    public static FileWriterService getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new FileWriterService();
        }
        return INSTANCE;
    }

    public void writeFile(File file, Rule rule)
    {
        try {

            String content = rule.toString();

            // Create file if it doesn't exist
            if (!file.exists()) {
                new CreateDroolsFile(rule.getTitle());
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private FileWriterService()
    {

    }
}
