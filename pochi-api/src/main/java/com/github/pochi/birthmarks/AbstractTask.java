package com.github.pochi.birthmarks;

import com.github.pochi.birthmarks.config.Configuration;

public abstract class AbstractTask<T> implements Task<T>{
    private T type;
    private Configuration config;

    public AbstractTask(T type, Configuration config){
        this.type = type;
        this.config = config;
    }

    @Override
    public T type(){
        return type;
    }

    @Override
    public Configuration configuration() {
        return config;
    }
}
