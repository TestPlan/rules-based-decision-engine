package controllers;

import models.Constraint;
import models.LogicalConjunction;
import models.Operator;
import models.Rule;
import services.SerializationService;

import javax.swing.*;
import java.io.File;
import java.util.Stack;

/**
 * Created by Mike on 11/3/2016.
 */
public class Driver_MikeM
{
    public static void main(String[] args) throws Exception{

        /*String ruleName = "Test";

        System.out.println(ruleName.getClass().getName());

        *//**
         * OBJECT DATA
         * - Represents data points that can be retrieved from an external source and stored in a data structure for later use
         *//*
        models.Data data1 = new models.Data("TEMPERATURE", "DOUBLE", 15.5);
        models.Data data2 = new models.Data("NAME" , "STRING" , "Mathew");
        models.Data data3 = new models.Data("IS_HUNGRY" , "BOOLEAN" , true);


        *//**
         * CONSTRAINTS
         * - Specific constraints placed on different pieces of ObjectData objects.
         *//*
        Constraint constraint = new Constraint(data1, models.Operator.GREATER_THAN, 65.3 , models.LogicalConjunction.NONE);
        Constraint constraint2 = new Constraint(data2, Operator.EQUAL_TO, "Trae", LogicalConjunction.AND);

        *//**
         * CONSTRAINT LIST
         * - Creates an ArrayList of all constraints for the specific rule.
         *//*
        models.ConstraintList list = new models.ConstraintList();
        list.add(constraint);
        list.add(constraint2);

        *//**
         * CONDITIONAL ELEMENT
         * - Creates the conditional element of a rule which consists of the pattern binding, pattern type and list of constraints
         *//*
        models.ConditionalElement cond1 = new models.ConditionalElement("$o" , "ObjectData", list);

        *//**
         * CONDITIONAL ELEMENT LIST
         * - Consists of an ArrayList of conditional elements of a rule
         *//*
        models.ConditionalElementList condList = new models.ConditionalElementList();
        condList.add(cond1);

        *//**
         * ACTIONS
         * - Basic action to be done if conditions are true.
         *//*
        models.Action action = new models.Action("System.out.println(\"Jump\");");

        *//**
         * RULE
         *
         *//*
        Rule rule = new Rule(ruleName , condList , action);

        SerializationService ss = SerializationService.getInstance();
        ss.serialize(rule, chooseFileLocation() + rule.getTitle());

        Rule rule1 = new Rule();
        rule1 = (Rule) ss.deserialize(chooseFileLocation());

        System.out.println(rule.toString());*/

        String infix = "( ( getData(\"sub.temp\")<50 && getData(\"sub.doubleTemp\")>100 ) or getData(\"airplaine.size\")>1000 )";
        System.out.println(infixToPostfix(infix));
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

    /**
     *
     * @param topStack
     * @param nextOp
     * @return Returns true if the next operator going onto the stack is greater than the operator on top of the stack.
     */
    public static boolean operatorPriority(String topStack, String nextOp){
        boolean value = false;

        if(topStack.equals("||"))
        {
            if(nextOp.equals("&&") || nextOp.equals("("))
            {
                value = true;
            }
            else if(nextOp.equals("||"))
            {
                value = false;
            }
        }
        else if(topStack.equals("&&"))
        {
            if(nextOp.equals("("))
            {
                value = true;
            }
            else
            {
                value = false;
            }
        }
        else if(topStack.equals("("))
        {
            if(!nextOp.equals(")"))
            {
                value = true;
            }
        }
        return value;
    }

    public static String infixToPostfix(String InfExpression)
    {
        Stack<String> opStack = new Stack<String>();
        StringBuilder builder = new StringBuilder();
        String[] input = InfExpression.split(" ");

        for(int i = 0; i < input.length; i++){
            String value = input[i];
            value = value.trim();

            if(!isOperator(value))
            {
                builder.append(value + " ");
            }
            else if(opStack.isEmpty() || operatorPriority(opStack.peek(), value))
            {
                if(!value.equals(")")){
                    opStack.push(value);
                }
            }
            else
            {
                while(!opStack.empty() && !opStack.peek().equals("(") && !operatorPriority(opStack.peek(), value)){
                    builder.append(opStack.pop() + " ");
                }
                if(!opStack.isEmpty() && value.equals(")"))
                {
                    opStack.pop();
                }
                else if(opStack.isEmpty() || operatorPriority(opStack.peek(), value)){
                    opStack.push(value);
                }
            }
        }
        while(!opStack.isEmpty()){
            builder.append(opStack.pop() + " ");
        }
        return builder.toString();
    }

    public static boolean isOperator(String str)
    {
        boolean valid = false;
        String operator;
        switch(str){
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
