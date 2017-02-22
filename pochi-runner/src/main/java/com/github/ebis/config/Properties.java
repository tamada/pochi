package com.github.ebis.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Properties implements Serializable{
    private static final long serialVersionUID = -7716178184705408249L;

    @JsonProperty("properties")
    private Map<String, String> properties = new HashMap<>();

    public void forEach(Consumer<Item> consumer){
        properties.entrySet()
        .stream().map(entry -> new Item(entry))
        .forEach(consumer);
    }

    public Item[] toArray(){
        return properties.entrySet()
                .stream().map(entry -> new Item(entry))
                .toArray(size -> new Item[size]);
    }

    public ItemValue property(ItemKey key){
        String keyString = key.toString();
        return new ItemValue(properties.get(keyString));
    }

    public boolean contains(ItemKey key){
        String keyString = key.toString();
        return properties.containsKey(keyString);
    }

    public void put(ItemKey key, ItemValue value){
        String keyString = key.toString();
        String valueString = value.toString();
        properties.put(keyString, valueString);
    }

    public void put(Item item){
        properties.put(item.key().toString(), 
                item.value().toString());
    }
}
