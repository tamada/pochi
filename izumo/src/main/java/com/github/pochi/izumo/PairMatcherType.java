package com.github.pochi.izumo;

import java.io.Serializable;
import java.util.Objects;

public class PairMatcherType implements Serializable {
    private static final long serialVersionUID = -3415982143006262812L;

    private String name;

    public PairMatcherType(String name) {
        this.name = name;
    }

    public boolean is(String typeName) {
        return Objects.equals(typeName, name);
    }

    public boolean equals(Object other) {
        return other instanceof PairMatcherType
                && Objects.equals(name, ((PairMatcherType)other).name);
    }

    public int hashCode() {
        return Objects.hash(name, getClass().getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
