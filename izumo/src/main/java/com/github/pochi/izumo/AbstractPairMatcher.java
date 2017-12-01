package com.github.pochi.izumo;

public abstract class AbstractPairMatcher<T> implements PairMatcher<T> {
    private PairMatcherType type;

    public AbstractPairMatcher(PairMatcherType type) {
        this.type = type;
    }

    @Override
    public PairMatcherType type() {
        return type;
    }
}
