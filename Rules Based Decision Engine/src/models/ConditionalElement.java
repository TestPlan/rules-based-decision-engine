package models;

/**
 * Created by Klaydon Balicanta on 10/12/2016
 * Modified by Klaydon Balicanta on 10/26/2016
 */

public class ConditionalElement
{
    private String patternBinding = ""; //instantiate with an empty string to alleviate nullPointer error
    private String pattern;
    private ConstraintList constraints;

    /**
     * Default Constructor
     *
     * If, upon instantiation, the parameters are empty, this ConditionalElement object
     * will always be considered true (Reference: 5.8.3.1 and figure 5.12 in the following link:
     * https://docs.jboss.org/drools/release/5.2.0.Final/drools-expert-docs/html/ch05.html#d0e3761)
     */
    public ConditionalElement() {
        this.pattern = "eval";
        this.constraints = new ConstraintList();
        constraints.add(new Constraint());
    }

    /**
     * Overloaded Constructor - pattern binding included
     *
     * @param patternBinding
     * @param pattern
     * @param constraints
     */
    public ConditionalElement(String patternBinding, String pattern, ConstraintList constraints)
        {
        this.patternBinding = patternBinding;
        this.pattern = pattern;
        this.constraints = constraints;
    }

    /**
     * Overloaded Constructor - pattern binding excluded
     *
     * @param pattern
     * @param constraints
     */
    public ConditionalElement(String pattern, ConstraintList constraints)
    {
        //this.patternBinding = "";
        this.pattern = pattern;
        this.constraints = constraints;
    }

    /**
     * getCondition gets this Conditions cond value
     *
     * @return cond
     */
    public String getCondition()
    {
        return this.toString();
    }

    /**
     * setCondition sets this Conditions cond value to a new
     *
     * @param newConstraint
     */
    public void setCondition(Constraint newConstraint)
    {
        this.constraints.add(newConstraint);
    }

    /**
     * toString Creates a String representation of the ConditionalElement to be used in a Drools Rule
     *
     * @return String of this ConditionalElement Object
     */
    public String toString()
    {
        String result = "";
        if (!this.patternBinding.equals(""))
        {
            result = this.patternBinding + " : " + this.pattern + " (" + this.constraints.toString() + ") ";
        }
        else
        {
            result = this.pattern + " (" + this.constraints.toString() + ") ";
        }
        return result;
    }

    /*GETTERS AND SETTERS*/

    private void setPatternBinding(String newPB)
    {
        this.patternBinding = newPB;
    }

    private String getPatternBinding()
    {
        return this.patternBinding;
    }

    private void setPattern(String newP)
    {
        this.pattern = newP;
    }

    private String getPattern()
    {
        return this.pattern;
    }

    private void setRestraint(ConstraintList newCList)
    {
        this.constraints = newCList;
    }

    private String getRestraint()
    {
        return this.constraints.toString();   //not sure if this method call will exist
    }
}
