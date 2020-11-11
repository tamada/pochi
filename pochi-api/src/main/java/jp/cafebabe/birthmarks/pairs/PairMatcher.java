package jp.cafebabe.birthmarks.pairs;

import java.io.Serializable;
import java.util.stream.Stream;

import jp.cafebabe.birthmarks.Task;
import jp.cafebabe.birthmarks.entities.Pair;

public interface PairMatcher<T extends Serializable> extends Task<PairMatcherType>{
    @Override
    PairMatcherType type();

    Stream<Pair<T>> match(Streamable<T> birthmarks);

    Stream<Pair<T>> match(Streamable<T> birthmarks1, Streamable<T> birthmarks2);

    long count(Streamable<T> birthmarks);

    long count(Streamable<T> birthmarks1, Streamable<T> birthmarks2);
}
