package com.github.ebis.birthmarks;

public class MutableElements<T> extends Elements<T> {
    private static final long serialVersionUID = -8534120646567554373L;

    public final void add(Element<T> element) {
        elements.add(element);
    }

    public final void add(T element) {
        add(new Element<T>(element));
    }
}
