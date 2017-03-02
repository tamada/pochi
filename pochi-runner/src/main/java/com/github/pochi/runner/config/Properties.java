package com.github.pochi.runner.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Properties implements Serializable{
    private static final long serialVersionUID = -7716178184705408249L;

    @JsonProperty("properties")
    private Map<String, String> map = new HashMap<>();

    public void forEach(Consumer<Item> consumer){
        map.entrySet()
        .stream().map(Item::new)
        .forEach(consumer);
    }

    public Item[] toArray(){
        return map.entrySet()
                .stream().map(Item::new)
                .toArray(Item[]::new);
    }

    public ItemValue property(ItemKey key){
        String keyString = key.toString();
        return new ItemValue(map.get(keyString));
    }

    public boolean contains(ItemKey key){
        String keyString = key.toString();
        return map.containsKey(keyString);
    }

    public void put(ItemKey key, ItemValue value){
        String keyString = key.toString();
        String valueString = value.toString();
        map.put(keyString, valueString);
    }

    public void put(Item item){
        map.put(item.key().toString(), 
                item.value().toString());
    }
}
