package com.github.pochi.runner.birthmarks.pairs;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.pairs.PairMaker;
import com.github.pochi.birthmarks.pairs.PairMakerBuilder;
import com.github.pochi.birthmarks.pairs.PairMakerType;

public class RoundRobinPairMakerBuilder implements PairMakerBuilder {
    private boolean withSamePair = false;
    private PairMakerType type;

    public RoundRobinPairMakerBuilder() {
        this(false);
    }
    
    public RoundRobinPairMakerBuilder(boolean withSamePair) {
        this.withSamePair = withSamePair;
        type = initializeType();
    }

    private PairMakerType initializeType() {
        if(withSamePair)
            return new PairMakerType("RoundRobinWithSamePair");
        return new PairMakerType("RoundRobin");
    }

    @Override
    public PairMakerType type() {
        return type;
    }

    @Override
    public PairMaker build(Configuration config) {
        return new RoundRobinPairMaker(type(), withSamePair, config);
    }
}
