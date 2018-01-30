package com.github.pochi.runner.birthmarks.pairs;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.pairs.PairMatcher;
import com.github.pochi.birthmarks.pairs.PairMatcherBuilder;
import com.github.pochi.birthmarks.pairs.PairMatcherType;

public class RoundRobinPairMatcherBuilder implements PairMatcherBuilder {
    private boolean withSamePair = false;
    private PairMatcherType type;

    public RoundRobinPairMatcherBuilder() {
        this(false);
    }
    
    public RoundRobinPairMatcherBuilder(boolean withSamePair) {
        this.withSamePair = withSamePair;
        type = initializeType();
    }

    private PairMatcherType initializeType() {
        if(withSamePair)
            return new PairMatcherType("RoundRobinWithSamePair");
        return new PairMatcherType("RoundRobin");
    }

    @Override
    public PairMatcherType type() {
        return type;
    }

    @Override
    public PairMatcher build(Configuration config) {
        return new RoundRobinPairMatcher(type(), withSamePair, config);
    }
}
