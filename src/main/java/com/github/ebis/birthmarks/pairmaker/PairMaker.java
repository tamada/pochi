package com.github.ebis.birthmarks.pairmaker;

import java.util.stream.Stream;

import com.github.ebis.birthmarks.ExtractionResults;
import com.github.ebis.birthmarks.Pair;

public interface PairMaker<T> {
    Stream<Pair<T>> stream(ExtractionResults<T> birthmarks);
}
