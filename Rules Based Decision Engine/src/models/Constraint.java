package models;

/**
 * <h3>Constraint.java</h3>- represents a Drools Rule constraint, which is an expression, i.e. 5 > 4 or
 * Temperature.getTemp() > 98.9. Is one of the components that makes up a Rule.
 *
 * @author Ian Markind
 * @version 1.0 - 10/22/2016.
 */
public class Constraint
{
    private Operator operator;
    private String leftHandSide;
    private String rightHandSide;
    private LogicalConjunction logicalConjunction;


    public Constraint()
    {
        leftHandSide = "";
        operator = Operator.EQUAL_TO;
        rightHandSide = "";
        logicalConjunction = LogicalConjunction.NONE;
    }

    public Constraint(String leftHandSide, Operator operator, String rightHandSide, LogicalConjunction logicalConjunction)
    {
        this.leftHandSide = leftHandSide;
        this.operator = operator;
        this.rightHandSide = rightHandSide;
        this.logicalConjunction = logicalConjunction;
    }

    // TODO: possible swap method, to change order of constraints

    /**
     * Compare two Constraint objects for equality
     *
     * @param constraint is the Constraint to be checked against for equality
     * @return whether or not the Constraints are equal
     */
    public boolean equals(Constraint constraint)
    {
        if (constraint == null)
        {
            return false;
        }

        boolean equal = false;

        if (this.getLeftHandSide().equals(constraint.getLeftHandSide()) && this.getOperator().toString().equals(constraint.getOperator().toString()) && this.getRightHandSide().equals(constraint.getRightHandSide()))
        {
            equal = true;
        }

        return equal;
    }

    @Override
    public int hashCode()
    {
        int result = 17;
        result = 31 * result + operator.hashCode();
        result = 31 * result + leftHandSide.hashCode();
        result = 31 * result + rightHandSide.hashCode();

        return result;
    }

    public Operator getOperator()
    {
        return operator;
    }

    public void setOperator(Operator operator)
    {
        this.operator = operator;
    }

    public String getLeftHandSide()
    {
        return leftHandSide;
    }

    public void setLeftHandSide(String leftHandSide)
    {
        this.leftHandSide = leftHandSide;
    }

    public String getRightHandSide()
    {
        return rightHandSide;
    }

    public void setRightHandSide(String rightHandSide)
    {
        this.rightHandSide = rightHandSide;
    }

    public LogicalConjunction getLogicalConjunction()
    {
        return logicalConjunction;
    }

    public void setLogicalConjunction(LogicalConjunction logicalConjunction)
    {
        this.logicalConjunction = logicalConjunction;
    }

    /**
     * Only prints correctly if the first Constraint on the list has a LogicalConjunction of NONE and
     * every other Constraint has a LogicalConjunction of either AND or OR.
     *
     * @return a list of Constraints formatted for Drools syntax
     */
    @Override
    public String toString()
    {
        return this.getLogicalConjunction().toString() + " " +
            this.getLeftHandSide() + " " +
            this.getOperator().toString() + " " +
            this.getRightHandSide() + " ";
    }
}
