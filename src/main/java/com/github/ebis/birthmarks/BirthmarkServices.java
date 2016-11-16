package com.github.ebis.birthmarks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

import com.github.ebis.birthmarks.computers.Computer;
import com.github.ebis.birthmarks.extractor.Extractor;

public class BirthmarkServices {
    private Map<BirthmarkType, BirthmarkService<?>> services = new HashMap<>();

    public void registerService(BirthmarkService<?> service) {
        services.put(service.type(), service);
    }

    public <T> void registerService(Extractor<T> extractor, Computer comparator) {
        registerService(new BirthmarkService<T>(extractor, comparator));
    }

    public Iterator<BirthmarkService<?>> services() {
        return services.values().iterator();
    }

    public Stream<BirthmarkService<?>> stream(){
        return services.values().stream();
    }
}
