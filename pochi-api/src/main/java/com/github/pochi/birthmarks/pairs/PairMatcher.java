package com.github.pochi.birthmarks.pairs;

import java.util.stream.Stream;

import com.github.pochi.birthmarks.Task;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.Birthmarks;

public interface PairMatcher extends Task<PairMatcherType>{
    @Override
    public PairMatcherType type();

    public Stream<Pair<Birthmark>> match(Birthmarks birthmarks);

    public Stream<Pair<Birthmark>> match(Birthmarks birthmarks1, Birthmarks birthmarks2);
}
