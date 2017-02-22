package com.github.pochi.runner.config;

import java.io.Serializable;
import java.util.Objects;

public class ItemKey implements Serializable{
    private static final long serialVersionUID = -3402781919191167965L;

    private String key;

    public ItemKey(String key){
        this.key = key;
    }

    @Override
    public boolean equals(Object object){
        return object instanceof ItemKey
                && Objects.equals(key,
                        ((ItemKey)object).key);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(key);
    }

    @Override
    public String toString(){
        return key;
    }
}
