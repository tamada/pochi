package com.github.pochi.birthmarks.pairs;

import com.github.pochi.birthmarks.AbstractTask;
import com.github.pochi.birthmarks.config.Configuration;

public abstract class AbstractPairMatcher extends AbstractTask<PairMatcherType> implements PairMatcher {
    public AbstractPairMatcher(PairMatcherType type, Configuration config){
        super(type, config);
    }
}
