package com.github.ebis.rules;

public class ContainsRule extends Rule {
    public ContainsRule(String pattern) {
        super(pattern);
    }

    @Override
    public boolean isMatch(String name) {
        return name.contains(pattern());
    }

}
