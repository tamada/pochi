package com.github.pochi.izumo.builders;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.pairs.PairMatcher;
import com.github.pochi.birthmarks.pairs.PairMatcherBuilder;
import com.github.pochi.birthmarks.pairs.PairMatcherType;

public abstract class AbstractPairMatcherBuilder<T> implements PairMatcherBuilder<T> {
    private PairMatcherType type;

    public AbstractPairMatcherBuilder(PairMatcherType type) {
        this.type = type;
    }

    @Override
    public final PairMatcherType type() {
        return type;
    }

    @Override
    public abstract PairMatcher<T> build(Configuration config);
}
