package com.github.pochi.runner.birthmarks.pairs;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.pairs.PairMatcher;
import com.github.pochi.birthmarks.pairs.PairMatcherBuilder;
import com.github.pochi.birthmarks.pairs.PairMatcherType;

public class SameNamePairMatcherBuilder implements PairMatcherBuilder {

    @Override
    public PairMatcherType type() {
        return new PairMatcherType("SameNamePair");
    }

    @Override
    public PairMatcher build(Configuration config) {
        return new SameNamePairMatcher(config);
    }
}
