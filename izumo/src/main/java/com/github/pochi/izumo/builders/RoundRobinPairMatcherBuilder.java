package com.github.pochi.izumo.builders;

import java.util.Objects;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.pairs.PairMatcher;
import com.github.pochi.birthmarks.pairs.PairMatcherType;
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
        if(Objects.equals(type(), RoundRobinPairMatcher.TYPE))
            return new RoundRobinPairMatcher<>(type(), false, config);
        return new RoundRobinPairMatcher<>(type(), true, config);
    }

    private static PairMatcherType createType(boolean withSamePair) {
        if(withSamePair)
            return RoundRobinPairMatcher.SAME_PAIR_TYPE;
        return RoundRobinPairMatcher.TYPE;
    }
}
