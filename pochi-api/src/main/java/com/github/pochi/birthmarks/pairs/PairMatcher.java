package com.github.pochi.birthmarks.pairs;

import java.util.stream.Stream;

import com.github.pochi.birthmarks.Task;

public interface PairMatcher<T> extends Task<PairMatcherType>{
    @Override
    public PairMatcherType type();

    public Stream<Pair<T>> match(Streamable<T> birthmarks);

    public Stream<Pair<T>> match(Streamable<T> birthmarks1, Streamable<T> birthmarks2);
}
