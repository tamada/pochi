package com.github.ebis.rules;

import java.util.regex.Pattern;

public class RegexRule extends Rule {
    private Pattern pattern;

    public RegexRule(String regex) {
        super(regex);

        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean isMatch(String name) {
        return pattern.matcher(name).matches();
    }

}
