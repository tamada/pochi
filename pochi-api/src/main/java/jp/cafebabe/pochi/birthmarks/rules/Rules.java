package jp.cafebabe.pochi.birthmarks.rules;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Rules {
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

    public Stream<Rule> stream(){
        return list.stream();
    }

    public String toJson() {
        return stream().map(item -> item.toJson())
                .collect(Collectors.joining(",", "[", "]"));
    }

    @Override
    public String toString() {
        return list.stream()
                .map(rule -> rule.toString())
                .collect(Collectors.joining(",", "{", "}"));
    }
}
