package com.github.pochi.izumo;

public class DefaultRoundRobinPairMatcher<T> extends RoundRobinPairMatcher<T> {
    public DefaultRoundRobinPairMatcher() {
        super(new PairMatcherType("RoundRobin"), false);
    }
}
