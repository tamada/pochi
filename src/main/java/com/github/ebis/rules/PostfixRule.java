package com.github.ebis.rules;

public class PostfixRule extends Rule {
    public PostfixRule(String pattern) {
        super(pattern);
    }

    @Override
    public boolean isMatch(String name) {
        return name.endsWith(pattern());
    }

}
