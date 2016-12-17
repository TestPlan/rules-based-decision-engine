package services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * A Class which can be used to parse a .drl file into a Rule object
 *
 * Ability to store each rule as a HashMap
 * Key = title, value as the Rule
 */
public class RulesReader
{
    /**
     * @pathName - Stores the path where the file is located
     * @fileName - Stores the Name of the file
     * @line - Stores the Line after reading from the file
     */
    public String pathName = "./src//rules//";
    public String fileName = "avoid.drl";
    public HashMap<String, String> ruleList = new HashMap<String, String>();

    /**
     * Constructor Takes two Strings
     *
     * @param pathName Name of the Path
     * @param fileName Name of the file
     */
    public RulesReader(String pathName, String fileName)
    {
        this.pathName = pathName;
        this.fileName = fileName;
    }

    /**
     * Constructor Takes one Strings
     * @param fileName Name of the file
     * by Default the pathName will be the predefined path
     */
    public RulesReader(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * @return The path to the file
     */
    public String getPathName()
    {
        return pathName;
    }

    /**
     * @return The file's name
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * Sets pathName to given String
     *
     * @param pathName The pathName to set
     */
    public void setPathName(String pathName)
    {
        this.pathName = pathName;
    }

    /**
     * Sets fileName to given String
     *
     * @param fileName The name to set as the file's name
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * Appends all keys in the collection to a single String
     *
     * @return A String containing all the keys in the collection
     */
    public String printKeys()
    {
        String temp = "";
        for (String key : ruleList.keySet())
        {
            temp += "\n" + "Rule Key: " + key.toString();
        }
        return temp;
    }

    /**
     * Appends all keys and corresponding rules to a single String
     *
     * @return A String containing all the keys and rules in the collection
     */
    public String printMap()
    {
        String temp = "";
       // Iterator iterator = ruleList.entrySet().iterator();
        for (String key : ruleList.keySet())
        {
            temp += "\n" + "Rule Key: " + key.toString() + "\n" + ruleList.get(key);
        }
        return temp;
    }

    /**
     * Retreives a single rule by its Title
     *
     * @param title The title of the Rule to find
     * @return The Rule if it is found
     */
    public String getRule(String title)
    {
        if (ruleList.isEmpty())
        {
            readDroolsFile();
        }

        return ruleList.get(title).toString();
    }

    /**
     * Reads the file line by line and appends the current line to a String
     * Prints the entire file
     */
    public void readDroolsFile()
    {
        String line = "";
        try
        {
            // FileReader reads text files.
            FileReader fileReader = new FileReader(this.getPathName() + this.getFileName());

            //Reads the Current Line.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null)
            {
                String title = "";
                String ruleLines = "";

                if (line.startsWith("rule"))
                {
                    //Getting Rule Title
                    //  = line.substring(0, 4);
                    title = line.substring(4);
                    title = title.trim();
                    // System.out.println(title);

                    //Skipping Blank Lines
                    while ((line = bufferedReader.readLine()) == null)
                    {
                    }

                    if (line.contains("when"))
                    {
                        ruleLines += "when";
                        //Getting Rule from when to end
                        while (!(line = bufferedReader.readLine()).contains("end"))
                        {
                            ruleLines += line;
                        }
                        // System.out.println("when" + "\n" + ruleLines);
                        ruleList.put(title, ruleLines);

                    }
                }
            }
            // close files.
            bufferedReader.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch (IOException ex)
        {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }
}
