package com.github.ebis.birthmarks.pairs;

import java.util.stream.Stream;

import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.birthmarks.entities.Birthmarks;
import com.github.ebis.birthmarks.entities.Pair;
import com.github.ebis.birthmarks.entities.PairMakerType;

public class RoundRobinPairMaker extends AbstractPairMaker{
    private boolean includeSamePair = false;

    public RoundRobinPairMaker(){
        this(false);
    }

    public RoundRobinPairMaker(boolean includeSamePair){
        super(new PairMakerType("roundrobin"));
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
        if(includeSamePair) return 1;
        return 0;
    }

    @Override
    public Stream<Pair<Birthmark>> pairUpWith(Birthmarks birthmarks1, Birthmarks birthmarks2) {
        return birthmarks1.stream()
                .flatMap(birthmark1 -> birthmarks2.stream()
                        .map(birthmark2 -> new Pair<>(birthmark1, birthmark2)));
    }

    private static class Index{
        private int index;

        public Index(int index){
            this.index = index;
        }

        public int index(){
            int i = index;
            index++;
            return i;
        }
    }
}
