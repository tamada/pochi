package com.github.pochi.birthmarks.pairs;

import java.util.stream.Stream;

import com.github.pochi.birthmarks.Task;

public interface PairMatcher<T> extends Task<PairMatcherType>{
    @Override
    PairMatcherType type();

    Stream<Pair<T>> match(Streamable<T> birthmarks);

    Stream<Pair<T>> match(Streamable<T> birthmarks1, Streamable<T> birthmarks2);

    long count(Streamable<T> birthmarks);

    long count(Streamable<T> birthmarks1, Streamable<T> birthmarks2);
}
