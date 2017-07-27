package com.github.pochi.birthmarks.comparators;

import com.github.pochi.birthmarks.AbstractTask;
import com.github.pochi.birthmarks.config.Configuration;

public abstract class AbstractBirthmarkComparator extends AbstractTask<ComparatorType> implements BirthmarkComparator {
    public AbstractBirthmarkComparator(ComparatorType type, Configuration config){
        super(type, config);
    }
}
