package com.github.ebis.rules;

public abstract class Rule {
    private String pattern;

    public Rule(String pattern) {
        this.pattern = pattern;
    }

    protected String pattern(){
        return pattern;
    }

    public abstract boolean isMatch(String name);
}
