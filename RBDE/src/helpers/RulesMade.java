package helpers;

import models.Rule;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Mike on 10/9/2016.
 * Class is made up of a list of rules which stores their titles and actions
 * so that they can be easily imported, exported and displayed.
 * This class follows the singleton design pattern.
 */
public class RulesMade {

    private static RulesMade instance = null;

    private ArrayList<Rule> rules = new ArrayList<Rule>();
    private final String fileLoc = "C:\\Users\\Mike\\IdeaProjects\\Rules Based Decision Engine\\rules.txt";

    /**
     * Constructor for Rules Made that follows the singleton pattern;
     * cannot directly create a new instance.
     */
    private RulesMade(){
        ImportRulesMade();
    }

    /**
     * Method to add a new rule to the list of all rules
     * @param rule
     */
    public void addRule(Rule rule){
        this.rules.add(rule);
    }

    /**
     * Method to import the rule data from a text file, create new rules and store them in a list of rules.
     */
    public void ImportRulesMade(){

        Scanner ruleScanner;

        try{

            ruleScanner = new Scanner(new FileReader(fileLoc));

            while(ruleScanner.hasNextLine()){
                String txtLine = ruleScanner.nextLine();
                String[] titleAction = txtLine.split(",");
                rules.add(new Rule(titleAction[0], titleAction[1]));
            }

        }
        catch(FileNotFoundException e) {                                                //Exception if file is not found
            System.out.println("File not found!");
            System.exit(0);
        }
    }

    /**
     * Method to take the list of rules and store them in
     * a text file at the file location.
     */
    public void exportRulesMade(){
        PrintWriter outputStream = null;										//Writes the notes in notebook to the text file
        try{
            outputStream = new PrintWriter(fileLoc);
            for(int i = 0; i < rules.size(); i++){
                outputStream.println(rules.get(i).getTitle() + "," + rules.get(i).getAction());
            }
            outputStream.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Error opening file!");
            System.exit(0);
        }
    }

    /**
     * This method checks if there was already an instance of this class already created
     * and if not creates a new one and returns it.  If it was already created it returns
     * the one that was already made
     * @return
     */
    public static RulesMade getInstance(){
        if(instance == null){
            instance = new RulesMade();
        }
        return instance;
    }

    public ArrayList<Rule> getRules(){
        return this.rules;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public String toString(){
        String s = new String();

        for (int i = 0; i < this.rules.size(); i++) {
            s = s + this.rules.get(i).toString() + "\n";
        }

        return s;
    }

}
