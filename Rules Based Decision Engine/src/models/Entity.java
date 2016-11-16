package models;

import java.util.*;

/**
 * An Entity is a collection of Data objects.
 * <p>
 * An Entity represents a "thing" like, for example, a submarine.
 * The submarine may have a temperature, latitude, and longitude.
 * The HashMap field <code>map</code> is a collection of the above datapoints as Data objects.
 *
 * @author Michael Crinite
 */
public class Entity
{
    public String entityName;
    public HashMap<String, Data> data;


    public Entity(String entityname, HashMap<String, Data> newData)
    {
        this.entityName = entityname;
        this.data = newData;
    }

    public void setName(String Name)
    {
        this.entityName = Name;
    }

    public HashMap<String, Data> getData()
    {
        return this.data;
    }

    public void setData(HashMap<String, Data> data)
    {
        this.data = data;
    }


    public String getName()
    {
        return this.entityName;
    }

    public Object getValue(String str)
    {
        str = str.trim().toUpperCase();
        Data temp = null;
        temp = data.get(str);

        if (temp != null)
        {
            return temp.getData();
        }
        else
        {
            return null;
        }
    }

    public String toString()
    {
        String temp = "";
        temp += this.entityName;
        temp += "\n" + data;

        return temp;
    }

    //TODO: Append maps

    //TODO: Replace current map with new map
}
