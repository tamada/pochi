package jp.cafebabe.pochi.birthmarks;

import jp.cafebabe.pochi.birthmarks.config.Configuration;

public abstract class AbstractTask<T> implements Task<T>{
    private T type;
    private Configuration config;

    public AbstractTask(T type, Configuration config){
        this.type = type;
        this.config = config;
    }

    @Override
    public final T type(){
        return type;
    }

    @Override
    public final Configuration configuration() {
        return config;
    }
}
