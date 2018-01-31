package com.github.pochi.izumo;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import com.github.pochi.birthmarks.config.Configuration;

public class GuessedPairMatcher<T> extends AbstractPairMatcher<T> {
    private CorrespondenceChecker<T> checker;

    public GuessedPairMatcher(PairMatcherType type, Configuration config) {
        this(type, config, (item1, item2) -> Objects.equals(item1, item2));
    }

    public GuessedPairMatcher(PairMatcherType type, Configuration config, CorrespondenceChecker<T> checker) {
        super(type, config);
        this.checker = checker;
    }

    @Override
    public Stream<Pair<T>> match(Streamable<T> target) {
        return match(target, target);
    }

    @Override
    public Stream<Pair<T>> match(Streamable<T> target1, Streamable<T> target2) {
        return target1.stream()
                .map(item1 -> createPair(item1, target2))
                .filter(optional -> optional.isPresent())
                .map(pair -> pair.get());
    }

    private Optional<Pair<T>> createPair(T item1, Streamable<T> target) {
        return target.stream().filter(item2 -> checker.isCorrespond(item1, item2))
                .map(item2 -> new Pair<>(item1, item2))
                .findFirst();
    }
}
