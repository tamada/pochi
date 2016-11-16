package com.github.ebis.rules;

import java.util.ArrayList;
import java.util.List;

public class Rules {
    private List<Rule> rules = new ArrayList<>();

    public void add(Rule rule) {
        rules.add(rule);
    }

    public Rule get(int index) {
        return rules.get(index);
    }

    public boolean anyMatch(String className) {
        return rules.stream()
                .anyMatch(rule -> rule.isMatch(className));
    }

    public Rule remove(int index) {
        return rules.remove(index);
    }
}
