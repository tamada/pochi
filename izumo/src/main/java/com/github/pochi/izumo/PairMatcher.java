package com.github.pochi.izumo;

import java.util.stream.Stream;

import com.github.pochi.birthmarks.Task;

public interface PairMatcher<T> extends Task<PairMatcherType> {
    @Override
    PairMatcherType type();

    Stream<Pair<T>> match(Streamable<T> target);

    Stream<Pair<T>> match(Streamable<T> target1, Streamable<T> target2);
}