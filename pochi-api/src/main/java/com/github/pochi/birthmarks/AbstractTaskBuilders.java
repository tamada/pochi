package com.github.pochi.birthmarks;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Stream;

public class AbstractTaskBuilders<T, V extends TaskBuilder<T>> implements TaskBuilders<T, V>{
    private Map<T, V> builders = new HashMap<>();

    public AbstractTaskBuilders(Class<? extends V> targetClass){
        registerServices(targetClass);
    }

    @Override
    public V builder(T type){
        return builders.get(type);
    }

    @Override
    public boolean available(T type){
        return builders.containsKey(type);
    }

    @Override
    public Stream<T> availableTypes(){
        return builders.keySet()
                .stream();
    }

    private void registerServices(Class<? extends V> clazz){
        ServiceLoader<? extends V> loader = ServiceLoader.load(clazz);
        loader.forEach(this::register);
    }

    public void register(V service){
        T type = service.type();
        builders.put(type, service);
    }
}
