package models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Klaydon Balicanta on 10/25/2016.
 */
public class ConditionalElementList implements Serializable
{
    private ArrayList<ConditionalElement> conditionalElementsList;

    //TODO: Add LogicalConjunction

    /**
     * Default constructor
     */
    public ConditionalElementList()
    {
        conditionalElementsList = new ArrayList<ConditionalElement>();
    }

    /**
     * Parameterized constructor
     *
     * @param condElemList
     */
    public ConditionalElementList(ArrayList<ConditionalElement> condElemList)
    {
        this.conditionalElementsList = condElemList;
    }

    /**
     * @param condElem
     * @return
     */
    public boolean add(ConditionalElement condElem)
    {
        boolean added = false;

        if (condElem != null)
        {
            conditionalElementsList.add(condElem);
            added = true;
        }

        return added;
    }

    /**
     * @param indexToRemove
     * @return
     */
    public boolean remove(int indexToRemove)
    {
        boolean deleted = false;

        if (!conditionalElementsList.isEmpty() && conditionalElementsList.size() > indexToRemove)
        {
            conditionalElementsList.remove(indexToRemove);
            deleted = true;
        }

        return deleted;
    }

    /**
     * @param condElem
     * @return
     */
    public boolean remove(ConditionalElement condElem)
    {
        ConditionalElement conditionalElementToDelete = null;
        boolean deleted = false;

        for (ConditionalElement c : conditionalElementsList)
        {
            if (c.equals(condElem))
            {
                conditionalElementToDelete = c;
            }
        }

        if (!conditionalElementsList.isEmpty() && conditionalElementToDelete != null)
        {
            conditionalElementsList.remove(conditionalElementToDelete);
            deleted = true;
        }

        return deleted;
    }

    /**
     * @return
     */
    public ArrayList<ConditionalElement> getConditionalElementList()
    {
        return conditionalElementsList;
    }

    /**
     * @param newConditionalElementList
     */
    public void setConditionalElementList(ArrayList<ConditionalElement> newConditionalElementList)
    {
        this.conditionalElementsList = newConditionalElementList;
    }

    /**
     * @return
     */
    public String toString()
    {
        String result = "";
        for (int i = 0; i < conditionalElementsList.size(); i++)
        {
            result += conditionalElementsList.get(i).toString() + "\n";
        }
        return result;
    }
}
