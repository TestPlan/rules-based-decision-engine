package services;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will utilize a Scanner to parse a text file for data.
 * The data must be formatted as following:
 *      # Comment lines begin with pound/hash
 *      dataName : dataType : value
 *
 * Any lines not formatted as a comment or as data will cause an error
 *
 *
 * @author Michael Crinite
 * @version  0.1 - 10/8/2016
 */
public class InputReaderService {
	
	private static InputReaderService INSTANCE = null;
	private static ArrayList<String> content;
    private File file;          //The File being read

    
    DataConversionService data_svc = DataConversionService.getInstance();
    
    
    public static InputReaderService getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new InputReaderService();
			content = new ArrayList<String>();
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
    	this.file = new File(filename);
        try
        {
            Scanner s = new Scanner(file);
            String line="";
            
            while(!endOfFile(line = s.nextLine()) && s.hasNextLine())
            { 
                if (!line.isEmpty())
                	content.add(line);
            }
            s.close();
        }
        catch(FileNotFoundException e)
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
    public File getFile() {
        return file;
    }
    
    /**
     * Returns absolute file path of File object.
     * @return String - Absolute file path
     */
    public String getAbsolutePath() {
    	return file.getAbsolutePath();
    }

    /**
     * Set the File object to be read
     * @param file The file to be read
     */
    public void setFile(File file) {
       this.file = file;
    }
    
   
	/**
	 * Returns the ArrayList conversion of the parsed file
	 * @return ArrayList<String> 
	 */
	public ArrayList<String> getFileBuffer()
	{
		return content;
	}
	
	/**
	 * Clears all stored file data from ArrayList<String>.
	 */
	public void clearFileBuffer()
	{
		content.clear();
	}
	

}