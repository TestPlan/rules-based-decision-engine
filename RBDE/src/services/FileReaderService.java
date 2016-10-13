package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class FileReaderService 
{
	String filename = null;
	private static FileReaderService INSTANCE = null;

	/**
	 *  Contains list of configurable data for Scenario
	 */
	private ArrayList<String> content; 
	
	public static FileReaderService getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new FileReaderService();
		}
		return INSTANCE;
	}

	
	/**
	 * This method reads a file and stores data into ArrayList.
	 * @param path Path to file to be parsed.
	 */
	public void readFile(String path)
	{
		// Declares Buffer Reader
		BufferedReader br = null;
		filename = path;
		if (filename == null)
		{
			throw new IllegalArgumentException("FileReaderService::readFile() - No valid filename set");
		}
		try {
			// Initializes new BufferedReader
			br = new BufferedReader(new FileReader(path));
			String line;
			
			while ((line = br.readLine()) != null)
			{
				if (!isComment(line))
				{
					content.add(line);
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void setFileName(String filename)
	{
		this.filename = filename;
		
	}
	

	public String getFileName()
	{
		return this.filename;
	}
	
	/**
	 * Returns the ArrayList conversion of the parsed file
	 * @return ArrayList<String> 
	 */
	public ArrayList<String> getFileBuffer()
	{
		return content;
	}
	
	public void clearFileBuffer()
	{
		content.clear();
	}
	
	
	/**
	 * This method checks to see if first character of line is a comment. If so, line read from
	 * buffered reader is not saved.
	 * @param line - string currently being processed by buffered reader.
	 * @return true - if line is comment, false otherwise.
	 */
	private boolean isComment(String line)
	{
		//TODO: create an enum for all escape characters. Should be a separate class. Decouple!
		boolean val = false;
		char character = line.charAt(0);
		
		// Compare the first character of line to comment characters
		if(character == '#')
		{
			val = true;
		}
		
		return val;
		
	}
	
	
	
}
