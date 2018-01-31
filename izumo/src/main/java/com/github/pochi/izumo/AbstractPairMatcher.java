package com.github.pochi.izumo;

import com.github.pochi.birthmarks.AbstractTask;
import com.github.pochi.birthmarks.config.Configuration;

public abstract class AbstractPairMatcher<T> extends AbstractTask<PairMatcherType> implements PairMatcher<T>{
    public AbstractPairMatcher(PairMatcherType type, Configuration config) {
        super(type, config);
    }
}
