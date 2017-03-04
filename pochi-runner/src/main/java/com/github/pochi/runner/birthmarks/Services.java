package com.github.pochi.runner.birthmarks;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Stream;

public class Services<T, V extends Service<T>> {
    private Map<T, V> list = new HashMap<>();

    public Services(Class<? extends V> targetClass){
        registerServices(targetClass);
    }

    public V service(T type){
        return list.get(type);
    }

    public boolean available(T type){
        return list.containsKey(type);
    }

    public Stream<T> availableServices(){
        return list.keySet()
                .stream();
    }

    private void registerServices(Class<? extends V> clazz){
        ServiceLoader<? extends V> loader = ServiceLoader.load(clazz);
        loader.forEach(this::register);
    }

    public void register(V service){
        T type = service.type();
        list.put(type, service);
    }
}
