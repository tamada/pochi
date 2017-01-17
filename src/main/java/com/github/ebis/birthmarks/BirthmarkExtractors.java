package com.github.ebis.birthmarks;

import com.github.ebis.birthmarks.entities.BirthmarkType;

public class BirthmarkExtractors extends Services<BirthmarkType, BirthmarkExtractor>{
    public BirthmarkExtractors(){
        super(BirthmarkExtractor.class);
    }

    public BirthmarkType[] availableTypes(){
        return availableServices().toArray(size -> new BirthmarkType[size]);
    }
}
