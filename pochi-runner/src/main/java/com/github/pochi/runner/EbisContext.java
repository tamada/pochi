package com.github.pochi.runner;

import com.github.pochi.runner.birthmarks.BirthmarkExtractor;
import com.github.pochi.runner.birthmarks.BirthmarkSystem;
import com.github.pochi.runner.birthmarks.entities.BirthmarkType;

public class EbisContext {
    private BirthmarkSystem system = new BirthmarkSystem();

    public BirthmarkExtractor extractor(BirthmarkType type){
        return system.extractor(type);
    }
}
