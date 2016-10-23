package models;

import java.util.ArrayList;

/**
 * <h3>ConstraintList.java</h3> - a list of Constraints
 *
 * @author Ian Markind
 * @version 1.0 - 10/22/2016.
 */
public class ConstraintList
{
    private ArrayList<Constraint> constraintList;

    public ConstraintList()
    {
        constraintList = new ArrayList<Constraint>();
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

        if (constraintList.size() == 1)
        {
            output = constraintList.get(0).toString();
        }
        else
        {
            final int SIZE = constraintList.size();

            for (int i = 0; i < SIZE; i++)
            {
                if (i + 1 == SIZE) // last element in list has no comma after it
                {
                    output += constraintList.get(i).toString();
                }
                else
                {
                    output += constraintList.get(i).toString() + ", ";
                }
            }
        }

        return output;
    }
}
