package com.github.pochi.runner.birthmarks.rules;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rules {
    @JsonProperty("rules")
    private List<Rule> list;

    public Rules(){
        this(Stream.empty());
    }

    public Rules(Stream<Rule> stream){
        list = stream.collect(
                Collectors.toList());
    }

    public void add(Rule rule){
        list.add(rule);
    }

    public void removeAll(){
        list.clear();
    }

    public boolean anyMatch(String className){
        return list.stream()
                .anyMatch(rule -> rule.match(className));
    }

    @JsonProperty("rules")
    public Rule[] toArray(){
        return list.toArray(new Rule[list.size()]);
    }
}
