package com.github.pochi.birthmarks.extractors;

import com.github.pochi.birthmarks.AbstractTask;
import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.BirthmarkType;

public abstract class AbstractExtractor extends AbstractTask<BirthmarkType> implements Extractor {
    public AbstractExtractor(BirthmarkType type, Configuration config){
        super(type, config);
    }
}
