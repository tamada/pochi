package com.github.ebis.birthmarks.pairmaker;

import java.util.stream.Stream;

import com.github.ebis.birthmarks.ExtractionResults;
import com.github.ebis.birthmarks.Pair;

public class GuessedPairMaker<T> extends RoundRobinPairMaker<T> {
    @Override
    public Stream<Pair<T>> stream(ExtractionResults<T> birthmarks){
        return super.stream(birthmarks)
                .filter(pair -> pair.isSameNameButDifferentLocation());
    }
}
