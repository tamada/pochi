package jp.cafebabe.pochi.pairs.builders;

import java.io.Serializable;
import java.util.Objects;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairs.PairMatcher;
import jp.cafebabe.pochi.pairs.CorrespondenceChecker;
import jp.cafebabe.pochi.pairs.GuessedPairMatcher;

public class GuessedPairMatcherBuilder<T extends Serializable> extends AbstractPairMatcherBuilder<T>{
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
