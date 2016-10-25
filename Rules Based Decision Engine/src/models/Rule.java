package models;

import services.ActionCollectionServices;

/**
 * Created by Ian Markind on 10/8/2016.
 */
public class Rule
{
    private String title;
    private ConditionalElementList conditionalElemList;
    private Action action;
    ActionCollectionServices act_svc = ActionCollectionServices.getInstance();

    public Rule()
    {
    }

    public Rule(String title, ConditionalElementList conditionalElemList, Action action)
    {
        this.title = title;
        this.conditionalElemList = conditionalElemList;
        this.action = action;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public ConditionalElementList getConditionalElemList()
    {
        return conditionalElemList;
    }

    public void setConditionElement(ConditionalElementList conditionalElemList)
    {
        this.conditionalElemList = conditionalElemList;
    }

    public Action getAction()
    {
        return action;
    }

    public void setAction(Action action)
    {
        this.action = action;
    }

    @Override
    public String toString()
    {
        String result = "rule \"" + title + "\"\n" +
            "when \"" + conditionalElemList.toString() + "\"\n" +
            "then \"" + action.toString() + "\n\n" +
            "end";
        return result;
    }
}
