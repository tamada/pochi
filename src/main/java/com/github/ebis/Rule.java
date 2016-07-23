package com.github.ebis;

public class Rule {
    public enum Type {
        Contains, ExactMatch, Postfix, Prefix, Regex,
    };

    private String pattern;
    private Type type;

    public Rule(Type type, String pattern) {
        if (type == null) {
            throw new NullPointerException("type is not specified");
        }
        if (pattern == null) {
            throw new NullPointerException("pattern is not specified");
        }
        this.type = type;
        this.pattern = pattern;
    }

    public Type getType() {
        return type;
    }

    public boolean isFollow(String name) {
        if (name == null) {
            throw new NullPointerException("given name is null");
        }
        switch (getType()) {
        case Contains:
            return name.contains(pattern);
        case ExactMatch:
            return name.equals(pattern);
        case Postfix:
            return name.endsWith(pattern);
        case Prefix:
            return name.startsWith(pattern);
        case Regex:
            return name.matches(pattern);
        }
        // never reached here!
        throw new InternalError();
    }
}
