package com.github.pochi.birthmarks.entities;

import java.io.Serializable;
import java.util.Objects;

public class Element implements Serializable{
    private static final long serialVersionUID = -4912875456020999024L;

    private String item;

    public Element(String element){
        this.item = element;
    }

    public Element(Element element){
        this.item = element.item;
    }

    @Override
    public int hashCode(){
        return Objects.hash(item, getClass().getName());
    }

    @Override
    public boolean equals(Object object){
        return object instanceof Element
                && Objects.equals(item, 
                        ((Element)object).item);
    }

    @Override
    public String toString(){
        return item;
    }
}
