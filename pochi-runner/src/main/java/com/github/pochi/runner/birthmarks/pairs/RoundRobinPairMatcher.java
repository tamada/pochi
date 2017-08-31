package com.github.pochi.runner.birthmarks.pairs;

import java.util.stream.Stream;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.Birthmarks;
import com.github.pochi.birthmarks.pairs.AbstractPairMatcher;
import com.github.pochi.birthmarks.pairs.Pair;
import com.github.pochi.birthmarks.pairs.PairMatcherType;

public class RoundRobinPairMatcher extends AbstractPairMatcher{
    private boolean includeSamePair = false;

    protected RoundRobinPairMatcher(PairMatcherType type, boolean includeSamePair, Configuration config){
        super(type, config);
        this.includeSamePair = includeSamePair;
    }

    @Override
    public Stream<Pair<Birthmark>> match(Birthmarks birthmarks) {
        Index index = new Index(firstIndex());
        return birthmarks.stream()
                .flatMap(item1 -> matchPair(item1, index, birthmarks));
    }

    private Stream<Pair<Birthmark>> matchPair(Birthmark baseBirthmark, Index index, Birthmarks list){
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
    public Stream<Pair<Birthmark>> match(Birthmarks birthmarks1, Birthmarks birthmarks2) {
        return birthmarks1.stream()
                .flatMap(birthmark1 -> birthmarks2.stream()
                        .map(birthmark2 -> new Pair<>(birthmark1, birthmark2)));
    }
}
