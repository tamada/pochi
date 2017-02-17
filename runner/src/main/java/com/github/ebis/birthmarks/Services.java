package com.github.ebis.birthmarks;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Stream;

public class Services<T, V extends Service<T>> {
    private Map<T, V> services = new HashMap<>();

    public Services(Class<? extends V> targetClass){
        registerServices(targetClass);
    }

    public V service(T type){
        return (V)services.get(type);
    }

    public boolean available(T type){
        return services.containsKey(type);
    }

    public Stream<T> availableServices(){
        return services.keySet()
                .stream();
    }

    private void registerServices(Class<? extends V> clazz){
        ServiceLoader<? extends V> loader = ServiceLoader.load(clazz);
        loader.forEach(service -> register(service));
    }

    public void register(V service){
        T type = service.type();
        services.put(type, service);
    }
}
