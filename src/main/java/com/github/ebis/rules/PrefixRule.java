package com.github.ebis.rules;

public class PrefixRule extends Rule {
    public PrefixRule(String pattern) {
        super(pattern);
    }

    @Override
    public boolean isMatch(String name) {
        return name.startsWith(pattern());
    }

}
