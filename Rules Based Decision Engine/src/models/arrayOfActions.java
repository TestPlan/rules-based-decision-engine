package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by shiv on 10/12/2016.
 */
public class arrayOfActions{
    List<Action> actions;

    public arrayOfActions(){
        actions = new ArrayList<Action>();
    }

    public void add(Action action){
        actions.add(action);
    }

    public void remove(Action action){
        actions.remove(action);
    }

    public String toString(){
        String temp ="";
        Iterator<Action> iterator = actions.iterator();

        while(iterator.hasNext()){
            temp += "\n" + iterator.next();
        }
        return temp;
    }
}
