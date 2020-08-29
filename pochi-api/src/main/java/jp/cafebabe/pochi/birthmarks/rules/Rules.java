package jp.cafebabe.pochi.birthmarks.rules;

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

    public boolean match(String className){
        return list.stream()
                .anyMatch(rule -> rule.match(className));
    }

    @JsonProperty("rules")
    public Rule[] rules(){
        return list.stream()
                .toArray(Rule[]::new);
    }

    @Override
    public String toString() {
        return list.stream()
                .map(rule -> rule.toString())
                .collect(Collectors.joining(",", "{", "}"));
    }
}
