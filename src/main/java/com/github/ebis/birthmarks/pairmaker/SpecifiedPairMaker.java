package com.github.ebis.birthmarks.pairmaker;

import java.util.Map;
import java.util.stream.Stream;

import com.github.ebis.birthmarks.ExtractionResults;
import com.github.ebis.birthmarks.Pair;
import com.github.kunai.entries.ClassName;

public class SpecifiedPairMaker<T> extends RoundRobinPairMaker<T> {
    private Map<ClassName, ClassName> pairs;

    public SpecifiedPairMaker(Map<ClassName, ClassName> pairs){
        this.pairs.putAll(pairs);
    }

    @Override
    public Stream<Pair<T>> stream(ExtractionResults<T> birthmarks) {
        return super.stream(birthmarks).filter(pair -> filter(pair));
    }

    private boolean filter(Pair<T> pair){
        return pair.isMatchPair(pairs);
    }
}
