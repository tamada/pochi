package com.github.pochi.birthmarks;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Stream;

public class TaskBuilders<T, V extends TaskBuilder<T>> {
    private Map<T, V> builders = new HashMap<>();

    public TaskBuilders(Class<? extends V> targetClass){
        registerServices(targetClass);
    }

    public V builder(T type){
        return builders.get(type);
    }

    public boolean available(T type){
        return builders.containsKey(type);
    }

    public Stream<T> availableServices(){
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
