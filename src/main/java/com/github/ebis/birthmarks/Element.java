package com.github.ebis.birthmarks;

import java.io.Serializable;
import java.util.Objects;

/**
 * Birthmark element.
 * 
 * @author Haruaki Tamada
 */
public class Element<T> implements Serializable {
    /**
     * Serial version uid.
     */
    private static final long serialVersionUID = -4195076861983472333L;

    /**
     * Value of the birthmark element.
     */
    private T value;

    /**
     * Constructor.
     * 
     * @param value
     *            Specify the value of the element.
     */
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

    /**
     * returns the value of this element.
     * 
     * @return the value of this element.
     */
    public T getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * returns string representation of this object.
     */
    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}
