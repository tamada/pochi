package com.github.pochi.birthmarks.pairs;

import java.util.stream.Stream;

import com.github.pochi.birthmarks.Task;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.Birthmarks;

public interface PairMaker extends Task<PairMakerType>{
    @Override
    public PairMakerType type();

    public Stream<Pair<Birthmark>> pairUpWith(Birthmarks birthmarks);

    public Stream<Pair<Birthmark>> pairUpWith(Birthmarks birthmarks1, Birthmarks birthmarks2);
}
