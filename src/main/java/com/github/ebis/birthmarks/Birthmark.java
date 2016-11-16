package com.github.ebis.birthmarks;

import java.io.Serializable;

public class Birthmark<T> implements Serializable {
    private static final long serialVersionUID = 6826281245220307289L;

    private Elements<T> elements;
    private BirthmarkType type;

    public Birthmark(BirthmarkType type, Elements<T> elements) {
        this.type = type;
        this.elements = elements;
    }

    public final Elements<T> elements() {
        return elements;
    }

    public final BirthmarkType type() {
        return type;
    }
}