package com.github.ebis.birthmarks;

import java.util.stream.Stream;

import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.birthmarks.entities.Birthmarks;
import com.github.ebis.birthmarks.entities.Pair;
import com.github.ebis.birthmarks.entities.PairMakerType;

public interface PairMaker extends Service<PairMakerType>{
    @Override
    public PairMakerType type();

    public Stream<Pair<Birthmark>> pairUpWith(Birthmarks birthmarks);

    public Stream<Pair<Birthmark>> pairUpWith(Birthmarks birthmarks1, Birthmarks birthmarks2);
}
