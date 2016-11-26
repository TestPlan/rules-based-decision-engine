package services;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

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
     */
	ObjectCollectionService obj_svc = ObjectCollectionService.getInstance();
	EntityCollectionService entity_svc = EntityCollectionService.getInstance();

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
    public void parseText(java.util.ArrayList<String> lines)
    {
    	HashMap<String, Object> entity_map = new HashMap<String, Object>();

        selector = 1;
        Object[] data = new Object[3];
        String entity_name = "";
        boolean first_line = true;

        for (int i = 0; i < lines.size(); i++)
        {
            if (!isComment(lines.get(i)))
            {
                data = lines.get(i).split(":", 3);
                data[0] = data[0].toString().toUpperCase().trim();

            	if (first_line)
            	{
            		if(data[0].toString().trim() == "entity_name")
            			entity_name = data[2].toString().toUpperCase().trim();
            		else
            			entity_name = entity_svc.defaultName();
            		first_line = false;
            	}
            	else
            	{
                    entity_map.put(entity_name + "." + data[0] , dataConversion(data));
            	}
            }
        }
        obj_svc.putAll(entity_map);
    	entity_svc.put(new Entity(entity_name , entity_map.keySet()));

    }

    /**
	 * This method iterates through an array of Data objects and saves them into the ObjectCollectionService.
	 *
	 * @param data - Array of Data parsed from JSON file
	 */
	public void parseJson(Map<String,Object> map)
	{
		HashMap<String, Object> entity_map = new HashMap<String, Object>();

		String entity_name = (String) map.remove("entity.name");

		if(entity_name == null)
		{
			entity_name = entity_svc.defaultName();
		}

		entity_name = entity_name.toUpperCase();


		for(Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();)
		{
			String key = iterator.next();
			Object value = map.get(key);

			entity_map.put(entity_name + "." + key.toUpperCase(), dataConversion(value));
		}

		obj_svc.putAll(entity_map);

		entity_svc.put(new Entity(entity_name , entity_map.keySet()));
	}


	/**
	 * For JSON Parser, this method applies a cast to an Object data.
	 * @param obj
	 * @return obj as a casted datatype.
	 */
	private Object dataConversion(Object obj)
	{
		if(obj instanceof Double)
	    	return (double)obj;
		else if(obj instanceof Integer)
			return (int)obj;
		else if(obj instanceof Boolean)
			return (boolean)obj;
		else if(obj instanceof String)
		{
			if (isChar((String)obj))
				return Character.valueOf(charParser((String) obj));
			else
				return (String)obj;
		}

		return obj;

	}


    /**
     * For Text parser, this method checks the validity of the described datatype and formats
     * the associated str to the appropriate datatype.
     *
     * @return Object of the correct datatype.
     */
    //TODO: Add support for Array datatypes.
    private Object dataConversion(Object[] obj)
    {
    	String value = (String) obj[2];
    	value = value.trim();

        obj_type = null;
        Object data;
        if (getObjectType(obj[1].toString()))
        {
            switch (obj_type)
            {
                case STRING:
                	obj[2] = value;
                    break;
                case INT:
                	obj[2] = intParser(value);
                    break;
                case CHAR:
                	obj[2] = Character.valueOf(charParser(value));
                    break;
                case DOUBLE:
                    if (selector == 1)
                    {
                    	obj[2] = doubleParser(value);
                        break;
                    }
                    break;
                case BOOL:
                	obj[2] = boolParser(value);
                    break;
            }
        }
        else
        {
            throw new IllegalArgumentException("DataConversionService::dataChecker(): [" + obj[1].toString() + "] is NOT an accepted data type");
        }

        data = obj[2];
        return data;


    }


    /**
	 * This method parses string for Double value.
	 * @param str string containing Double value
	 * @return Double value of string
	 */
	private double doubleParser(String str)
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
	 * This method parses string for Integer value.
	 * @param str string containing Integer value
	 * @return Integer value of string
	 */
	private int intParser(String str)
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
	private char charParser(String str)
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


	/**
	 * This method parses string for boolean value.
	 * @param str string containing boolean value
	 * @return boolean value of string
	 */
	private boolean boolParser(String str)
    {
    	str = str.trim();
        boolean value;
        if (str.matches("^[Tt]{1}(RUE|rue)?$") || str.matches("^[1]{1}$"))
        {
            value = true;
        }
        else if (str.matches("^[Ff]{1}(ALSE|alse)?$") || str.matches("^[0]{1}$"))
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
	 * This method checks to see if str argument conforms to the conditions of a Character Object.
	 * String must contain a non-white space character.
	 * @param str arguement to be checked.
	 * @return true if str arguement is a char.
	 */
    private boolean isChar(String str)
	{
		boolean result = false;

		if (str.trim().length() == 1)
		{
			result = true;
		}
		return result;
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


    private boolean getObjectType(String str)
    {
        boolean result = false;

        for (ObjectType obj : ObjectType.values())
        {
            if (obj.getValue().equals(str.trim().toUpperCase()))
            {
                obj_type = obj;
                result = true;
            }
        }
        return result;
    }


    /**
    * Converts .drl Conditional Element from infix notation to postfix notation for object storage.
    * @param infix
    * @return postfix notation of infix string.
    */
   public String infixToPostfix(String infix)
   {
       Stack<String> opStack = new Stack<String>();
       StringBuilder builder = new StringBuilder();
       String[] input = infix.split(" ");

       for(int i = 0; i < input.length; i++)
       {
           String value = input[i].trim();

           if(!isOperator(value))
               builder.append(value + " ");

           else if(opStack.isEmpty() || operatorPriority(opStack.peek(), value))
           {
               if(!value.equals(")"))
               {
                   opStack.push(value);
               }
           }
           else
           {
               while(!opStack.empty() && !opStack.peek().equals("(") && !operatorPriority(opStack.peek(), value))
               {
                   builder.append(opStack.pop() + " ");
               }
               if(!opStack.isEmpty() && value.equals(")"))
               {
                   opStack.pop();
               }
               else if(opStack.isEmpty() || operatorPriority(opStack.peek(), value))
               {
                   opStack.push(value);
               }
           }
       }
       while(!opStack.isEmpty())
       {
           builder.append(opStack.pop() + " ");
       }
       return builder.toString();
   }

    /**
     * Converts Conditional Element object from its stored postfix notation to infix notation for .drl file building.
     * @param postfix - The stored parameters for the conditional element object in postfix notation
     * @return Infix notation of postfix string.
     */
   public String postfixToInfix(String postfix)
   {
       Stack<String> infStack = new Stack<String>();
       String[] input = postfix.split(" ");


       for(int i = 0; i < input.length; i++)
       {
           String value = input[i].trim();
           if(!isOperator(value))
           {
               infStack.push(value);
           }
           else
           {
               String operandB = infStack.pop();
               String operandA = infStack.pop();
               String answer = "( " + operandA + " " + value + " " + operandB + " )";
               infStack.push(answer);

           }

       }
       return infStack.pop();
   }

   /**
    * Method compares the current operator to the operator on the top of the operator stack.
    * @param topStack
    * @param nextOp
    * @return Returns true if the next operator going onto the stack is greater than the operator on top of the stack.
    */
   private boolean operatorPriority(String topStack, String nextOp)
   {
       boolean value = false;

       if(topStack.equals("||"))
       {
           if(nextOp.equals("&&") || nextOp.equals("("))
               value = true;

           else if(nextOp.equals("||"))
               value = false;
       }
       else if(topStack.equals("&&"))
       {
           if(nextOp.equals("("))
               value = true;
           else
               value = false;
       }
       else if(topStack.equals("("))
       {
           if(!nextOp.equals(")"))
               value = true;
       }
       return value;
   }


private  boolean isOperator(String str)
   {
       boolean valid = false;

       switch(str)
       {
           case "(": valid = true;
                     break;
           case ")": valid = true;
                     break;
           case "&&": valid = true;
                      break;
           case "and": valid = true;
                       break;
           case ",": valid = true;
                     break;
           case "||": valid = true;
                      break;
           case "or": valid = true;
                      break;
           default: break;

       }
       return valid;
   }
}
