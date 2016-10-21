package models;

/**
 * Created by Klaydon Balicanta on 10/12/2016
 */
public class Condition{
    private String cond = "Message = ";		/*for the purposes of this first iteration and testing,
											to allow test creation of rules*/
    private String patternBinding;
    private String pattern;
    private String restriction;
    

    /**
     * constructor
     * @param patternBinding 
     * @param pattern
     * @param restriction
     */
    public Condition(String patternBinding, String pattern, String restriction) {
        this.patternBinding = patternBinding;
        this.pattern = pattern;
        this.restriction = restriction;
    }

    /**
     * overloaded constructor for the purposes of a single string to be regexed later
     * for the purposes of this first iteration and testing,
	 * to allow test creation of rules
	 
     * @param input 
     */
    public Condition(String input) {
        cond += input;
    }

    /**
     * getCondition gets this Conditions cond value
	 *
     * @return cond 
     */
    public String getCondition() {
        return this.toString();
    }

    /**
     * setCondition sets this Conditions cond value to a new 
	 *
     * @param newCondition
     */
    public void setCondition(String newCondition) {
        cond = newCondition;
    }

    /**
     * toString Creates a String reprsentation of the Condition to be used in a Drools Rule
	 *
     * @return String of this Condition Object
     */
    public String toString() {
        //String result = this.patternBinding + ": " + this.pattern;
        return cond;
    }
}