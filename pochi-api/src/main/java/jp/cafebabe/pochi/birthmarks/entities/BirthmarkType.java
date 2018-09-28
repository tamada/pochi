package jp.cafebabe.pochi.birthmarks.entities;

import java.io.Serializable;
import java.util.Objects;

public final class BirthmarkType implements Serializable{
    private static final long serialVersionUID = 6123912530056878788L;

    public static final BirthmarkType UNKNOWN = new BirthmarkType("UNKNOWN");

    private String type;

    public BirthmarkType(String name){
        this.type = name;
    }

    @Override
    public int hashCode(){
        return Objects.hash(type, getClass().getName());
    }

    @Override
    public boolean equals(Object object){
        return object instanceof BirthmarkType
                && Objects.equals(type, 
                        ((BirthmarkType)object).type);
    }

    @Override
    public String toString(){
        return type;
    }
}
