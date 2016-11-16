package com.github.ebis.birthmarks.pairmaker;

import java.util.stream.Stream;

import com.github.ebis.birthmarks.ExtractionResult;
import com.github.ebis.birthmarks.ExtractionResults;
import com.github.ebis.birthmarks.Pair;

public class RoundRobinPairMaker<T> implements PairMaker<T> {

    @Override
    public Stream<Pair<T>> stream(ExtractionResults<T> birthmarks) {
        Index index = new Index();
        return birthmarks.stream().flatMap(item -> map(index, item, birthmarks));
    }

    private Stream<Pair<T>> map(Index index, ExtractionResult<T> b1, ExtractionResults<T> birthmarks){
        Stream<Pair<T>> stream = birthmarks.stream()
                .skip(index.index)
                .map(item -> new Pair<T>(b1, item));
        index.index++;
        return stream;
    }

    private static class Index{
        int index = 1;
    }

}

