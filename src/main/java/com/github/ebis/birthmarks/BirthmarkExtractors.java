package com.github.ebis.birthmarks;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import com.github.ebis.birthmarks.entities.BirthmarkType;

public class BirthmarkExtractors {
    private Map<BirthmarkType, BirthmarkExtractor> extractors = new HashMap<>();

    public BirthmarkExtractors(){
        registerExtractors();
    }

    public BirthmarkExtractor extractor(BirthmarkType type){
        return extractors.get(type);
    }

    public BirthmarkType[] availableTypes(){
        return extractors.keySet()
                .stream()
                .toArray(size -> new BirthmarkType[size]);
    }

    private void registerExtractors(){
        ServiceLoader<BirthmarkExtractor> loader = ServiceLoader
                .load(BirthmarkExtractor.class);
        loader.forEach(extractor -> register(extractor));
    }

    public void register(BirthmarkExtractor extractor){
        BirthmarkType type = extractor.type();
        extractors.put(type, extractor);
    }
}
