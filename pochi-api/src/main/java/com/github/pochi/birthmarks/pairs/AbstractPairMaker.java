package com.github.pochi.birthmarks.pairs;

import com.github.pochi.birthmarks.AbstractTask;
import com.github.pochi.birthmarks.config.Configuration;

public abstract class AbstractPairMaker extends AbstractTask<PairMakerType> implements PairMaker {
    public AbstractPairMaker(PairMakerType type, Configuration config){
        super(type, config);
    }
}
