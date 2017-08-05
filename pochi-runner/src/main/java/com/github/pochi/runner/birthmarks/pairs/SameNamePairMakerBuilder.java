package com.github.pochi.runner.birthmarks.pairs;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.pairs.PairMaker;
import com.github.pochi.birthmarks.pairs.PairMakerBuilder;
import com.github.pochi.birthmarks.pairs.PairMakerType;

public class SameNamePairMakerBuilder implements PairMakerBuilder {

    @Override
    public PairMakerType type() {
        return new PairMakerType("SameNamePair");
    }

    @Override
    public PairMaker build(Configuration config) {
        return new SameNamePairMaker(config);
    }
}
