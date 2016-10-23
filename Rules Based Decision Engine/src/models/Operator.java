package models;

/**
 * Enumeration class for supported operators.
 *
 * @author Trae X. Lewis, Ian Markind
 * @version 1.1
 */
public enum Operator
{
    EQUAL_TO("=="), GREATER_THAN(">"), LESS_THAN("<"), GREATER_EQUAL(">="), LESS_EQUAL("<="), NOT_EQUAL("!="),
    CONTAINS("contains"), NOT_CONTAINS("not contains"), MEMBER_OF("memberof"), MATCHES("matches"),
    NOT_MATCHES("not matches"), IN("in"), NOT_IN("not in");

    private String value;

    private Operator(String value)
    {
        this.value = value;
    }

    public String toString()
    {
        return value;
    }

}
