package models;

import java.util.HashMap;

/**
 * An Entity is a collection of Data objects.
 *
 * An Entity represents a "thing" like, for example, a submarine.
 * The submarine may have a temperature, latitude, and longitude.
 * The HashMap field <code>map</code> is a collection of the above datapoints as Data objects.
 *
 * @author Michael Crinite
 */
public class Entity
{
    private String name;
    private HashMap<String, Data> map;

    public Object getValue(String str)
    {
        str = str.trim().toUpperCase();
        Data temp = null;
        temp = map.get(str);

        if(temp != null)
        {
            return temp.getData();
        }else{
            return null;
        }
    }

    //TODO: Append maps

    //TODO: Replace current map with new map
}
