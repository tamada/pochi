package jp.cafebabe.pochi.pairs;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Pair;
import jp.cafebabe.birthmarks.pairs.AbstractPairMatcher;
import jp.cafebabe.birthmarks.pairs.PairMatcherType;
import jp.cafebabe.birthmarks.pairs.Streamable;

public class GuessedPairMatcher<T extends Serializable> extends AbstractPairMatcher<T> {
    public static final PairMatcherType TYPE = new PairMatcherType("Guessed");

    private CorrespondenceChecker<T> checker;

    public GuessedPairMatcher(PairMatcherType type, Configuration config, CorrespondenceChecker<T> checker) {
        super(type, config);
        this.checker = checker;
    }

    @Override
    public Stream<Pair<T>> match(Streamable<T> target) {
        return match(target, target);
    }

    public long count(Streamable<T> target1, Streamable<T> target2) {
        return match(target1, target2).count();
    }

    public long count(Streamable<T> target) {
        return match(target).count();
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
