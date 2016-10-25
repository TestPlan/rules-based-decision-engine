package models;

/**
 * @author Ian Markind
 * @version 1.0 - 10/23/2016.
 */
public enum LogicalConjunction
{
    AND("&&"), OR("||"), NONE("");

    private String value;

    LogicalConjunction(String value)
    {
        this.value = value;
    }

    public String toString()
    {
        return value;
    }
}
