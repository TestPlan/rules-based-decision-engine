package services;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * This class will utilize a Scanner to parse a text file for data.
 * The data must be formatted as following:
 * # Comment lines begin with pound/hash
 * dataName : dataType : value
 * <p>
 * Any lines not formatted as a comment or as data will cause an error
 *
 * @author Trae Lewis  Michael Crinite
 * @version 2.0 - 10/20/2016
 */

public class FileReaderService
{
    //Fields
    InputParserService parse_svc = InputParserService.getInstance();
    private static FileReaderService INSTANCE = null;

    /**
     * Singleton Constructor.
     *
     * @return Instance of InputReaderService
     */
    public static FileReaderService getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new FileReaderService();
        }
        return INSTANCE;
    }

    /**
     * Default constructor for objects of type FileReaderService
     */
    private FileReaderService(){}

    /**
     * Attempts to parse the given file.
     * The file must be formatted in the following way:
     * <p>
     * Any line beginning with a pound/hash (#) is a comment line
     * The data line must be formatted as "Name : datatype : value"
     * <p>
     * Any line not properly formatted, and not written as a comment, will return an error
     * unless it is past the properly formatted line (currently the method will stop parsing
     * as soon as it has received the proper expected values)
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> readTextFile(String filename)
    {
        ArrayList<String> lines = new ArrayList<String>();
        File file = new File(filename);

        try
        {
            Scanner s = new java.util.Scanner(file);
            String line = "";

            while (s.hasNextLine())
            {
                if (!line.isEmpty())
                {
                    lines.add(line);
                }
            }
            s.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Reads a JSON file and parses it as an Entity
     *
     * @param filename filepath for the JSON file
     */
    @SuppressWarnings("unchecked")
	public void readJsonFile(String filename)
    {
    	HashMap<String, Object> map = new HashMap<String,Object>();
        JSONParser parser = new JSONParser();

        try
        {
            Object obj = parser.parse(new FileReader(filename));

            JSONArray json_array = (JSONArray) obj;

            for(int i = 0; i < json_array.size(); i++)
            {
            	map.clear();
            	JSONObject json_object = (JSONObject) json_array.get(i);
            	map.putAll(json_object);

            	parse_svc.parseJson(map);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


   //TODO: Formerly importRulesMade() , finish importRules method.
/*	public void parseRule(String filename)
    {
        File file = new File(filename);

        try
        {
            Scanner s = new java.util.Scanner(file);

            while (s.hasNextLine())
            {
                String txtLine = s.nextLine();
                @SuppressWarnings("unused") String[] titleAction = txtLine.split(",");
                //Rule r = new Rule(titleAction[0], titleAction[1], titleAction[2], titleAction[3], titleAction[4]);
                //rules.put(titleAction[0], r);
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();

        }
    }*/

}
