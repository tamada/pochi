package jp.cafebabe.izumo.builders;

import java.util.Objects;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairs.PairMatcher;
import jp.cafebabe.izumo.CorrespondenceChecker;
import jp.cafebabe.izumo.GuessedPairMatcher;

public class GuessedPairMatcherBuilder<T> extends AbstractPairMatcherBuilder<T>{
    private CorrespondenceChecker<T> checker;

    public GuessedPairMatcherBuilder() {
        this((item1, item2) -> Objects.equals(item1, item2));
    }

    public GuessedPairMatcherBuilder(CorrespondenceChecker<T> checker) {
        super(GuessedPairMatcher.TYPE);
        this.checker = checker;
    }

    @Override
    public PairMatcher<T> build(Configuration config) {
        return new GuessedPairMatcher<>(type(), config, checker);
    }
}
