package com.github.pochi.birthmarks.extractors;

import com.github.pochi.birthmarks.AbstractTask;
import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.BirthmarkType;

public abstract class AbstractBirthmarkExtractor extends AbstractTask<BirthmarkType> implements BirthmarkExtractor {
    public AbstractBirthmarkExtractor(BirthmarkType type, Configuration config){
        super(type, config);
    }
}
