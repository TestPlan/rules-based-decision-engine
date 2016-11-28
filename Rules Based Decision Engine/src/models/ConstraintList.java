package models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <h3>ConstraintList.java</h3> - a list of Constraints
 *
 * @author Ian Markind
 * @version 1.0 - 10/22/2016.
 */
//TODO: SHOULD rethink the whole arrayList thing.
//TODO: but this class may be obsolete.
public class ConstraintList implements Serializable
{
    /**
	 *
	 */
	private static final long serialVersionUID = -2784615968462618057L;
	private ArrayList<Constraint> constraintList;

    public ConstraintList()
    {
        constraintList = new ArrayList<>();
    }

    /**
     * Use this constructor if there is exactly one known Constraint to be added to the ConstraintList
     * @param c
     */
    public ConstraintList(Constraint c)
    {
        constraintList.add(c);
    }

    public ConstraintList(ArrayList<Constraint> constraintList)
    {
        this.constraintList = constraintList;
    }

    /**
     * Adds a Constraint to the list if it is not null
     *
     * @param constraint is the Constraint to be added
     */
    public boolean add(Constraint constraint)
    {
        boolean added = false;

        if (constraint != null)
        {
            constraintList.add(constraint);
            added = true;
        }

        return added;
    }

    /**
     * Removes a Constraint from the collection, based on the list index passed in
     *
     * @param index is the index Constraint in the list to be removed
     * @return whether or not the Constraint was successfully removed
     */
    public boolean remove(int index)
    {
        boolean deleted = false;

        if (!constraintList.isEmpty() && constraintList.size() > index)
        {
            constraintList.remove(index);
            deleted = true;
        }

        return deleted;
    }

    /**
     * Removes a Constraint from the collection, based on the object passed in
     *
     * @param constraint is the Constraint to be removed
     * @return whether or not the Constraint was successfully removed
     */
    public boolean remove(Constraint constraint)
    {
        Constraint constraintToDelete = null;
        boolean deleted = false;

        for (Constraint c : constraintList)
        {
            if (c.equals(constraint))
            {
                constraintToDelete = c;
            }
        }

        if (!constraintList.isEmpty() && constraintToDelete != null)
        {
            constraintList.remove(constraintToDelete);
            deleted = true;
        }

        return deleted;
    }

    /**
     * TODO: throw exception if invalid?
     * Checks if a constraintList is valid.
     * To be valid:
     * 1. The first Constraint must have a logicalConjunction of NONE
     * 2. Every other Constraint in the list must have a LogicalConjunction != NONE (AND | OR)
     * @return whether ot not the list of Constraints is valid
     */
    public boolean isValid()
    {
        if (!constraintList.get(0).getLogicalConjunction().equals(LogicalConjunction.NONE))
        {
            System.err.println("Invalid ConstraintList: First Constraint must have a LogicalConjunction of NONE.\n");
            return false;
        }

        for (int i = 1; i < constraintList.size(); i++)
        {
            if (constraintList.get(i).getLogicalConjunction().equals(LogicalConjunction.NONE))
            {
                System.err.println("Invalid ConstraintList: element # " + i + " has a LogicalConjunction of NONE.\n" +
                                    "Only the first element of a ConstraintList can have this value.\n" +
                                    "All other elements must have a LogicalConjunction of AND | OR\n");
                return false;
            }
        }
        System.out.println("ConstraintList is Valid\n");

        return true;
    }

    public ArrayList<Constraint> getConstraintList()
    {
        return constraintList;
    }

    public void setConstraintList(ArrayList<Constraint> constraintList)
    {
        this.constraintList = constraintList;
    }

    @Override
    public String toString()
    {
        String output = "";

        for (Constraint c: constraintList)
        {
            output += c.toString();
        }

        return output;
    }
}
