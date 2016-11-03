package models;

import services.ActionCollectionService;

import java.io.Serializable;

/**
 * Created by Ian Markind on 10/8/2016.
 */
public class Rule implements Serializable
{
    private String title;
    private ConditionalElementList conditionalElemList;
    private Action action;
    transient ActionCollectionService act_svc = ActionCollectionService.getInstance();

    private static final long serialVersionUID = 75643827956748L;

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
        String result =
            "import models.*;\n" +
            "dialect \"mvel\"\n\n" +
            "rule \"" + title + "\"\n" +
            "when \n    " + conditionalElemList.toString() + "\n" +
            "then \n    " + action.toString() + "\n\n" +
            "end";
        return result;
    }
}
