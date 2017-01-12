package com.github.ebis.birthmarks.entities;

import java.util.Objects;

public class Element {
    private String element;

    public Element(String element){
        this.element = element;
    }

    public Element(Element element){
        this.element = element.element;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(element);
    }

    @Override
    public boolean equals(Object object){
        return object instanceof Element &&
                equals((Element)object);
    }

    public boolean equals(Element item){
        return Objects.equals(
                element, item.element);
    }

    @Override
    public String toString(){
        return element;
    }
}
