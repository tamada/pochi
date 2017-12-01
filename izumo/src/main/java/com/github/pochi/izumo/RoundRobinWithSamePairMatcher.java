package com.github.pochi.izumo;

public class RoundRobinWithSamePairMatcher<T> extends RoundRobinPairMatcher<T> {
    public RoundRobinWithSamePairMatcher() {
        super(new PairMatcherType("RoundRobinWithSamePair"), true);
    }
}
