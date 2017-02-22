package com.github.pochi.runner.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pochi.runner.birthmarks.rules.Rule;
import com.github.pochi.runner.birthmarks.rules.Rules;

public class Configuration {
    @JsonProperty("rules")
    private Rules rules = new Rules();

    @JsonProperty("classpaths")
    private Classpaths classpaths = new Classpaths();

    @JsonProperty("properties")
    private Properties properties = new Properties();

    @JsonProperty
    public Rule[] rules(){
        return rules.toArray();
    }

    public boolean isSystemName(String className){
        return rules.anyMatch(className);
    }

    public void add(Rule rule){
        rules.add(rule);
    }

    @JsonProperty
    public Classpath[] classpaths(){
        return classpaths.toArray();
    }

    public ClassLoader classLoader(){
        return classpaths.buildClassLoader();
    }

    public void add(Classpath classpath){
        classpaths.add(classpath);
    }

    @JsonProperty
    public Item[] properties(){
        return properties.toArray();
    }

    public ItemValue property(ItemKey key){
        return properties.property(key);
    }

    public void put(ItemKey key, ItemValue value){
        properties.put(key, value);
    }
}
