package com.github.ebis.birthmarks;

import java.util.Objects;

public class BirthmarkType {
    private String name;

    public BirthmarkType(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BirthmarkType) {
            return Objects.equals(name, ((BirthmarkType) obj).name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
