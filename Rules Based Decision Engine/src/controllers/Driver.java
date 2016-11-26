package controllers;

import models.*;
import helpers.*;
import models.Action;
import services.FileWriterService;
import services.RuleCollectionService;

import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;


/**
 * @author Ian Markind, Trae X. Lewis, Michael Crinite
 */
public class Driver
{
	public static void main(String[] args) throws Exception
    {
    	HashMap<Object, Object> map = new HashMap<Object,Object>();
        JSONParser parser = new JSONParser();
        
        try
        {
            Object obj = parser.parse(new FileReader(System.getenv("RBDE_HOME")+"/temp.json"));
            
            JSONArray json_array = (JSONArray) obj;
            
            for(int i = 0; i < json_array.size(); i++)
            {
            	JSONObject json_object = (JSONObject) json_array.get(i);
            	
            	map.putAll(json_object);
            	
                for(Iterator<?> iterator = map.keySet().iterator(); iterator.hasNext();) 
                    {
                        String key = (String) iterator.next();
                        System.out.println(key + ": " + json_object.get(key) );
                    }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        
    }

    /**
     * This method's purpose is to give the user access to their file system so that they can choose the file
     * they want to deserialize, or the directory they want their serialized file in.
     */
    public static String chooseFileLocation(){
        String fileLoc = new String();

        JFileChooser fc = new JFileChooser(".");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.showOpenDialog(null);
        fileLoc = fc.getSelectedFile().getAbsolutePath() + "\\";

        return fileLoc;
    }

}
