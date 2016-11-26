package services;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import models.Entity;

public interface Collectable<T> 
{
    HashMap<String, Entity> map = new HashMap<String, Entity>();

	public boolean containsKey(String key);	
	public boolean containsValue(T value);
	public T get(String key);
    public T put(String key, T value);
    public T remove(String key);
	public void clear();
	public Set<Entry<String, T>> entrySet();
    public void putAll(HashMap<String,T> map);
    public String toString();
	
}
