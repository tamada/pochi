package com.github.pochi.runner.birthmarks.pairs;

import java.util.stream.Stream;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.Birthmarks;
import com.github.pochi.birthmarks.pairs.AbstractPairMaker;
import com.github.pochi.birthmarks.pairs.Pair;
import com.github.pochi.birthmarks.pairs.PairMakerType;

public class RoundRobinPairMaker extends AbstractPairMaker{
    private boolean includeSamePair = false;

    protected RoundRobinPairMaker(PairMakerType type, boolean includeSamePair, Configuration config){
        super(type, config);
        this.includeSamePair = includeSamePair;
    }

    @Override
    public Stream<Pair<Birthmark>> pairUpWith(Birthmarks birthmarks) {
        Index index = new Index(firstIndex());
        return birthmarks.stream()
                .flatMap(item1 -> makePair(item1, index, birthmarks));
    }

    private Stream<Pair<Birthmark>> makePair(Birthmark baseBirthmark, Index index, Birthmarks list){
        return list.stream()
                .skip(index.index())
                .map(birthmark -> new Pair<>(baseBirthmark, birthmark));
    }

    private int firstIndex(){
        if(includeSamePair)
            return 0;
        return 1;
    }

    @Override
    public Stream<Pair<Birthmark>> pairUpWith(Birthmarks birthmarks1, Birthmarks birthmarks2) {
        return birthmarks1.stream()
                .flatMap(birthmark1 -> birthmarks2.stream()
                        .map(birthmark2 -> new Pair<>(birthmark1, birthmark2)));
    }
}
