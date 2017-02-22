package com.github.pochi.runner.birthmarks;

import com.github.pochi.runner.birthmarks.entities.BirthmarkType;

public class BirthmarkExtractors extends Services<BirthmarkType, BirthmarkExtractor>{
    public BirthmarkExtractors(){
        super(BirthmarkExtractor.class);
    }

    public BirthmarkType[] availableTypes(){
        return availableServices().toArray(size -> new BirthmarkType[size]);
    }
}
