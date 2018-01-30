package com.github.pochi.birthmarks.config;

import java.util.Map;

public class Item {
    private ItemKey key;
    private ItemValue value;

    public Item(ItemKey key, ItemValue value){
        this.key = key;
        this.value = value;
    }

    Item(Map.Entry<String, String> entry){
        this(new ItemKey(entry.getKey()),
                new ItemValue(entry.getValue()));
    }

    public ItemKey key(){
        return key;
    }

    public ItemValue value(){
        return value;
    }
}
