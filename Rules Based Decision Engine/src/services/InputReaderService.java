package services;

import java.io.*;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.ObjectData;
import models.Rule;

/**
 * This class will utilize a Scanner to parse a text file for data.
 * The data must be formatted as following:
 *      # Comment lines begin with pound/hash
 *      dataName : dataType : value
 *
 * Any lines not formatted as a comment or as data will cause an error
 *
 *
 * @author Trae Lewis  Michael Crinite
 * @version  2.0 - 10/20/2016
 */

public class InputReaderService 
{
	
	ParserService parse_svc = ParserService.getInstance();
    private File file;          //The File being read

	private static InputReaderService INSTANCE = null;

    
    /**
     * Singleton Constructor.
     * @return Instance of InputReaderService
     */
    public static InputReaderService getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new InputReaderService();
		}
		return INSTANCE;
	}
    

    /**
     * Attempts to parse the given file.
     * The file must be formatted in the following way:
     *
     * Any line beginning with a pound/hash (#) is a comment line
     * The data line must be formatted as "Name : datatype : value"
     *
     * Any line not properly formatted, and not written as a comment, will return an error
     * unless it is past the properly formatted line (currently the method will stop parsing
     * as soon as it has received the proper expected values)
     */
    public void readFile(String filename)
    {
    	java.util.ArrayList<String> lines = new java.util.ArrayList<String>(); 
    	this.file = new File(filename);
        
    	try
        {
            java.util.Scanner s = new java.util.Scanner(file);
            String line="";
            
            while(!endOfFile(line = s.nextLine()) && s.hasNextLine())
            { 
                if (!line.isEmpty())
                	lines.add(line);
            }
            s.close();
        }
        catch(FileNotFoundException e)
        {
        	e.printStackTrace();
        }
    }
    
    public void readJSONFile(String filename)
    {
        try 
    	{
        	BufferedReader reader = new BufferedReader(new FileReader(filename));
        	Gson gson = new GsonBuilder().create();
        	ObjectData[] data = gson.fromJson(reader, ObjectData[].class);
        	parse_svc.parseJsonObjects(data);
        	
		} catch (FileNotFoundException e) 
    	{
			e.printStackTrace();
		}
    }
    
    public void readRuleFile(String filename)
    {
    	try
    	{
    		BufferedReader reader = new BufferedReader(new FileReader(filename));
    		Gson gson = new GsonBuilder().create();
    		Rule[] data = gson.fromJson(reader, Rule[].class);
    		//TODO: create parseRuleFile(data) method
    	} catch (FileNotFoundException e)
    	{
    		e.printStackTrace();
    	}
    	
    }

    /**
     * Checks current line for end of file character sequence. If found, everything after
     * sequence is discarded.
     * @param line - Current line in file being parsed.
     * @return true - if end of file sequence is detected.
     */
    private boolean endOfFile(String line)
    {
    	//TODO: Make eof string configurable. Should not be hard coded.
    	return line.startsWith("eof;");
    }
    
    
    /**
     * Returns the File object referenced
     * @return File object being read
     */
    public File getFile() 
    {
        return file;
    }
    
    /**
     * Returns absolute file path of File object.
     * @return String - Absolute file path
     */
    public String getAbsolutePath() 
    {
    	return file.getAbsolutePath();
    }

    /**
     * Set the File object to be read
     * @param file The file to be read
     */
    public void setFile(File file) 
    {
       this.file = file;
    }
    

}
