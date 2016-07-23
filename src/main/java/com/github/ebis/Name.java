package com.github.ebis;

import java.util.Objects;

public class Name implements Comparable<Name>{
    private String name;

    public Name(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Name cn) {
        return name.compareTo(cn.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Name) {
            return Objects.equals(name, ((Name) obj).name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
