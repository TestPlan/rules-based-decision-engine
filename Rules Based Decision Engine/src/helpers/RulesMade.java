package helpers;

import models.Rule;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Mike on 10/9/2016.
 */
public class RulesMade {

    private static RulesMade instance = null;

    private ArrayList<Rule> rules = new ArrayList<Rule>();
    private final String fileLoc = "C:\\Users\\Mike\\IdeaProjects\\Rules Based Decision Engine\\rules.txt";

    protected RulesMade(){
        ImportRulesMade();
    }

    public void addRule(Rule rule){
        this.rules.add(rule);
    }

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

    public static RulesMade getInstance(){
        if(instance == null){
            instance = new RulesMade();
        }
        return instance;
    }

    public ArrayList<Rule> getRules(){
        return this.rules;
    }

    @Override
    public String toString(){
        String s = new String();

        for (int i = 0; i < this.rules.size(); i++) {
            s = s + this.rules.get(i).toString() + "\n";
        }

        return s;
    }

}
