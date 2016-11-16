package com.github.ebis.rules;

public class MatchRule extends Rule {
    public MatchRule(String pattern) {
        super(pattern);
    }

    @Override
    public boolean isMatch(String name) {
        return name.equals(pattern());
    }

}
