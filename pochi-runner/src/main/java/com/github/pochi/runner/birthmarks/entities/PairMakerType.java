package com.github.pochi.runner.birthmarks.entities;

import java.io.Serializable;
import java.util.Objects;

public final class PairMakerType implements Serializable{
    private static final long serialVersionUID = -3169312619260242474L;

    private String type;

    public PairMakerType(String name){
        this.type = name;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(type);
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof PairMakerType)
            return equals((PairMakerType)object);
        return false;
    }

    public boolean equals(PairMakerType name){
        return Objects.equals(
                type, name.type);
    }

    @Override
    public String toString(){
        return type;
    }
}
