package services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by shiv on 10/23/2016.
 * <p>
 * Needs Some Editing for More Functionality
 */
public class RulesReader
{
    /*
    *   @pathName - Stores the path where the file is located
    *   @fileName - Stores the Name of the file
    *   @line     - Stores the Line after reading from the file
     */
    public  String pathName = "./src//rules//";
    public  String fileName = "avoid.drl";
    /*
     * Constructor Takes two Strings
     * pathName : Name of the Path
     * fileName : Name of the file
     */
    public RulesReader(String pathName, String fileName){
        this.pathName = pathName;
        this.fileName = fileName;
    }
    /*
     * Constructor Takes one Strings
     * fileName : Name of the file
     * by Default the pathName will be the predefined path
     */
    public RulesReader( String fileName){
        this.fileName = fileName;
    }

   /*
    * Returns pathName
    */
    public String getPathName(){
        return pathName;
    }
    /*
     * Returns fileName
     */
    public String getFileName(){
        return fileName;
    }
    /*
     * Sets pathName to given String
     */
    public void setPathName(String pathName)
    {
        this.pathName = pathName;
    }

    /*
     * Sets fileName to given String
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /*
     * Reads the file line by line and stores the current line to a String line
     * and Prints the entire file
     */
    public  void readDroolsFile()
    {
        String line="";
        try
        {
            // FileReader reads text files.
            FileReader fileReader = new FileReader(this.getPathName() + this.getFileName());

            //Reads the Current Line.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null)
            {
                System.out.println(line);
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
