package com.github.ebis.config;

import java.io.Serializable;
import java.util.Map;

public class Item implements Serializable{
    private static final long serialVersionUID = 3577182012083504792L;

    private ItemKey key;
    private ItemValue value;

    public Item(Map.Entry<String, String> entry){
        this(entry.getKey(), 
                entry.getValue());
    }

    public Item(String key, String value){
        this.key = new ItemKey(key);
        this.value = new ItemValue(value);
    }

    public boolean is(ItemKey otherKey){
        return key.equals(otherKey);
    }

    public boolean is(ItemValue otherValue){
        return key.equals(otherValue);
    }

    public ItemKey key(){
        return key;
    }

    public ItemValue value(){
        return value;
    }
}
