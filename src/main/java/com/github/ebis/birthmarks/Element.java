package com.github.ebis.birthmarks;

import java.io.Serializable;
import java.util.Objects;

/**
 * Birthmark element.
 * 
 * @author Haruaki Tamada
 */
public class Element<T> implements Serializable {
    private static final long serialVersionUID = -4195076861983472333L;

    private T value;

    public Element(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Element<?>) {
            @SuppressWarnings("unchecked")
            T value = ((Element<T>) o).getValue();
            return Objects.equals(getValue(), value);
        }
        return false;
    }

    public T getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}