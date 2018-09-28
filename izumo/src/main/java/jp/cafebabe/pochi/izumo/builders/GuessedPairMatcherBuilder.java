package jp.cafebabe.pochi.izumo.builders;

import java.util.Objects;

import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcher;
import jp.cafebabe.pochi.izumo.CorrespondenceChecker;
import jp.cafebabe.pochi.izumo.GuessedPairMatcher;

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
