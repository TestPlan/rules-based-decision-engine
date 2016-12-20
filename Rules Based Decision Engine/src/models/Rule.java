package models;

import services.ActionCollectionService;

import java.io.Serializable;

/**
 * Created by Ian Markind on 10/8/2016.
 */
public class Rule implements Serializable
{
    private String title;
    private Integer salience;
    private ConditionalElementList conditionalElemList;
    private ActionList actionList;
    transient ActionCollectionService act_svc = ActionCollectionService.getInstance();

    private static final long serialVersionUID = 75643827956748L;

    public Rule()
    {
    }

    public Rule(String title, Integer salience, ConditionalElementList conditionalElemList, ActionList actionList)
    {
        this.title = title;
        this.salience = salience;
        this.conditionalElemList = conditionalElemList;
        this.actionList = actionList;
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

    public ActionList getActionList()
    {
        return actionList;
    }

    public void setActionList(ActionList actionList)
    {
        this.actionList = actionList;
    }

    @Override
    public String toString()
    {
        String result =

            "rule \"" + title + "\"\n";
            if(salience != null)
            {
                result += "salience " + salience + "\n";
            }
           result += "when \n" + conditionalElemList.toString() + "\n" +
            "then \n" + actionList.toString() +
            "end";
        return result;
    }

    public void setSalience(Integer salience)
    {
        this.salience = salience;
    }
    public Integer getSalience()
    {
       return salience;
    }
}
