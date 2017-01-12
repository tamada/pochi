package com.github.ebis.birthmarks;

import com.github.ebis.birthmarks.entities.BirthmarkType;

public class BirthmarkSystem {
    private BirthmarkExtractors extractors = new BirthmarkExtractors();

    public BirthmarkExtractor extractor(BirthmarkType type){
        return extractors.extractor(type);
    }

    public BirthmarkType[] availableExtractors(){
        return extractors.availableTypes();
    }
}
