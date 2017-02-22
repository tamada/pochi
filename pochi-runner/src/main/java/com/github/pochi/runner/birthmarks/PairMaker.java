package com.github.pochi.runner.birthmarks;

import java.util.stream.Stream;

import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.Birthmarks;
import com.github.pochi.runner.birthmarks.entities.Pair;
import com.github.pochi.runner.birthmarks.entities.PairMakerType;

public interface PairMaker extends Service<PairMakerType>{
    @Override
    public PairMakerType type();

    public Stream<Pair<Birthmark>> pairUpWith(Birthmarks birthmarks);

    public Stream<Pair<Birthmark>> pairUpWith(Birthmarks birthmarks1, Birthmarks birthmarks2);
}
