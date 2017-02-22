package com.github.pochi.runner.birthmarks.rules;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rules {
    @JsonProperty("rules")
    private List<Rule> rules;

    public Rules(){
        this(Stream.empty());
    }

    public Rules(Stream<Rule> stream){
        rules = stream.collect(
                Collectors.toList());
    }

    public void add(Rule rule){
        rules.add(rule);
    }

    public void removeAll(){
        rules.clear();
    }

    public boolean anyMatch(String className){
        return rules.stream()
                .anyMatch(rule -> rule.match(className));
    }

    @JsonProperty("rules")
    public Rule[] toArray(){
        return rules.toArray(new Rule[rules.size()]);
    }
}
