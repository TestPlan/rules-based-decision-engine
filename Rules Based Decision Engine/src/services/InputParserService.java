package services;


import models.Data;
import models.Entity;
import models.ObjectType;

/**
 * This class is responsible for parsing the text data from Input file and storing it into its
 * proper data objects.
 *
 * @author Trae X. Lewis
 * @version 2.0 10/20/2016
 */
public class InputParserService
{

    /*
     * GLOBAL SERVICES
     */ ObjectCollectionService obj_svc = ObjectCollectionService.getInstance();

    private Data obj_data;
    private Entity entity;
    private ObjectType obj_type;
    private static InputParserService INSTANCE = null;
    private int selector = 0;

    /**
     * Retrieves the static instance of FileParserService.
     *
     * @return Instance of FileParserService.
     */
    public static InputParserService getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new InputParserService();

        }
        return INSTANCE;
    }


    /**
     * Takes an array of lines of a text file and turns them into an objectdata objects.
     *
     * @param lines - Line of text file.
     */
    public void parseTextLine(java.util.ArrayList<String> lines)
    {
        selector = 1;
        String[] data = new String[3];
        for (String sl : lines)
        {
            obj_data.clearData();

            if (!isComment(sl))
            {
                sl = sl.trim();
                data = sl.split(":", 3);
                obj_data.setName(data[0].trim());
                obj_data.setType(data[1].trim());
                obj_data.setData(data[2].trim());
                if (dataChecker())
                {
                    obj_svc.insertDataObject(obj_data.getName(), obj_data);
                }
            }
        }
    }

    /**
     * This method iterates through an array of Data objects and saves them into the ObjectCollectionService.
     *
     * @param data - Array of Data parsed from JSON file
     */
    public void parseJsonObjects(Data[] data)
    {
        // Removes previously stored Data objects stored from other files
        //TODO: Add configurable to allow option to concatenate data from multiple files
        //TODO: Add JSON feed functionality and support
        //TODO: Change this to create an Entity of data objects rather than Data
        obj_svc.clearObjectService();

        for (Data od : data)
        {
            this.obj_data = od;
            if (dataChecker())
            {
                obj_svc.insertDataObject(obj_data.getName(), obj_data);
            }
        }
    }


    /**
     * Checks if argument is a valid ObjectType.
     *
     * @return true if dataType is a valid enumeration.
     */
    private boolean dataChecker()
    {
        boolean result = false;
        obj_type = null;

        if (getObjectType())
        {
            result = true;
            switch (obj_type)
            {
                case STRING:
                    break;
                case INT:
                    obj_data.setData(intParse(obj_data.getData().toString()));
                    break;
                case CHAR:
                    obj_data.setData(Character.valueOf(charParse(obj_data.getData().toString())));
                    break;
                case DOUBLE:
                    if (selector == 1)
                    {
                        obj_data.setData(doubleParse(obj_data.getData().toString()));
                        break;
                    }
                    break;
                case BOOL:
                    obj_data.setData(boolParse(obj_data.getData().toString().toUpperCase()));
                    break;
                default:
            }
        }
        else
        {
            // Throws exception if not accepted data type.
            throw new IllegalArgumentException("DataConversionService::dataChecker():135 - [" + obj_data.getType() + "] is NOT an accepted data type");
        }
        return result;


    }


    /**
     * For Data objects that have a declared type of DOUVLE, this method checks the value of the Data type field
     * and verifies that it is a double in a string representation.
     *
     * @param str String representation of Data type field.
     * @return Double object holding the value of the specified string.
     */
    private double doubleParse(String str)
    {
        double value = 0.0;
        if (str.matches("^[\\-]{0,1}[\\d]*[\\.][\\d]+$"))
        {
            value = Double.valueOf(str);
        }
        else if (str.matches("^[\\-]{0,1}\\d+$"))
        {
            value = Double.valueOf(str + ".0");
        }

        return value;
    }

    /**
     * For Data objects that have a declared type of INT, this method checks the value of the Data type field
     * and verifies that it is an integer in a string representation.
     *
     * @param str String representation of Data type field.
     * @return Integer object holding the value of the specified string.
     */
    private int intParse(String str)
    {
        int value = 0;
        if (str.matches("^[\\-]{0,1}\\d+$"))
        {
            value = Integer.valueOf(str);
        } // Does string match a double between -∞ and +∞ with?
        else if (str.matches("^[\\-]{0,1}[\\d]*[\\.][\\d]+$"))
        {
            value = Integer.valueOf(str.substring(0, str.indexOf('.')));
        }
        else
        {
            throw new IllegalArgumentException("ParserService:intParse - Expected Integer: Returned: " + str.toString());
        }
        return value;
    }


    /**
     * This method checks input string for correct amount of characters to be considered a char data type.
     * If str.length() != 1 , then str does not conform.
     *
     * @param str - argument to be transformed to char data type.
     * @return char representation of string
     */
    private char charParse(String str)
    {
        char ch;

        str.trim();
        if (str.length() != 1)
        {
            throw new IllegalArgumentException("DataConversionService::charParse - Input stream exceeds maximum length of char: [Input value: " + str + " ]");
        }

        ch = str.charAt(0);

        return ch;
    }


    // TODO: add support for binary 0,1 for true/false condition
    // TODO: make sure true/false are only characters in string.
    private boolean boolParse(String str)
    {
        boolean value;
        if (str.matches("^[T]{1}(RUE)?$"))
        {
            value = true;
        }
        else if (str.matches("^[F]{1}(ALSE)?$"))
        {
            value = false;
        }
        else
        {
            throw new IllegalArgumentException("ParserService:boolParse - Expected Boolean: Returned: " + str.toString());
        }

        return value;
    }


    /**
     * This method checks to see if first character of line is a comment. If so, line read from
     * buffered reader is not saved.
     *
     * @param line - string currently being processed by buffered reader.
     * @return true - if line is comment, false otherwise.
     */
    private boolean isComment(String line)
    {
        //TODO: create an enum for all escape characters. Should be a separate class. Decouple!
        boolean val = false;
        char character = line.charAt(0);

        // Compare the first character of line to comment characters
        if (character == '#')
        {
            val = true;
        }
        return val;
    }


    private boolean getObjectType()
    {
        boolean result = false;

        for (ObjectType obj : ObjectType.values())
        {
            if (obj.getValue().equals(obj_data.getType()))
            {
                obj_type = obj;
                result = true;
            }
        }
        return result;
    }
}
