package com.github.pochi.runner.config;

import java.io.Serializable;
import java.util.Objects;

public class ItemValue implements Serializable{
    private static final long serialVersionUID = -126911335465964143L;

    private String value;

    public ItemValue(String value){
        this.value = value;
    }

    @Override
    public boolean equals(Object object){
        return object instanceof ItemValue
                && Objects.equals(value,
                        ((ItemValue)object).value);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(value);
    }

    @Override
    public String toString(){
        return value;
    }
}
