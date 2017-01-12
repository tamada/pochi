package com.github.ebis;

import com.github.ebis.birthmarks.BirthmarkExtractor;
import com.github.ebis.birthmarks.BirthmarkSystem;
import com.github.ebis.birthmarks.entities.BirthmarkType;

public class EbisContext {
    private BirthmarkSystem system = new BirthmarkSystem();

    public BirthmarkExtractor extractor(BirthmarkType type){
        return system.extractor(type);
    }
}
