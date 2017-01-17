package com.github.kunai.entries;

import java.io.Serializable;
import java.util.Objects;

public class Name implements Serializable{
    private static final long serialVersionUID = -3739333182235166571L;

    private String name;

    public Name(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object other){
        return other instanceof Name
            && Objects.equals(name,
                    ((Name)other).name);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public String toString(){
        return name;
    }
}
