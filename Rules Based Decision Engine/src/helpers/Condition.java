package helpers;

/**
 * Created by Klaydon Balicanta on 10/12/2016
 */
public class Condition{
    private String cond = "Message = ";
    
    private String patternBinding;
    private String pattern;
    private String restriction;
    

    /**
     * constructor
     * @param patternBinding
     * @param pattern
     * @param restriction
     */


    /**
     * overloaded constructor for the purposes of a single string to be regexed later
     *
     * @param input
     */
    public Condition(String input) {
        cond += input;
    }

    /**
     *
     * @return
     */
    public String getCondition() {
        return this.toString();
    }

    /**
     *
     * @param newCondition
     */
    public void setCondition(String newCondition) {
        cond = newCondition;
    }

    /**
     *
     * @return
     */
    public String toString() {
        //String result = this.patternBinding + ": " + this.pattern;
        return cond;
    }
}