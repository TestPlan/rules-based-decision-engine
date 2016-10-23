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

    public Constraint(String leftHandSide, Operator operator, String rightHandSide)
    {
        this.leftHandSide = leftHandSide;
        this.operator = operator;
        this.rightHandSide = rightHandSide;
    }

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

    @Override
    public String toString()
    {
        return this.getLeftHandSide() + " " + this.getOperator().toString() + " " + this.getRightHandSide();
    }
}
