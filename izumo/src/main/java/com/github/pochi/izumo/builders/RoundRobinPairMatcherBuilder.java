package com.github.pochi.izumo.builders;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.izumo.PairMatcher;
import com.github.pochi.izumo.PairMatcherType;
import com.github.pochi.izumo.RoundRobinPairMatcher;

public class RoundRobinPairMatcherBuilder<T> extends AbstractPairMatcherBuilder<T>{
    public RoundRobinPairMatcherBuilder(boolean withSamePair) {
        super(createType(withSamePair));
    }

    public RoundRobinPairMatcherBuilder() {
        this(false);
    }

    @Override
    public PairMatcher<T> build(Configuration config) {
        if(type().is("RoundRobin"))
            return new RoundRobinPairMatcher<>(type(), false, config);
        return new RoundRobinPairMatcher<>(type(), true, config);
    }

    private static PairMatcherType createType(boolean withSamePair) {
        if(withSamePair)
            return new PairMatcherType("RoundRobinWithSamePair");
        return new PairMatcherType("RoundRobin");
    }
}
