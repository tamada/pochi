package jp.cafebabe.pochi.pairs.builders;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairs.PairMatcher;
import jp.cafebabe.pochi.pairs.CorrespondenceChecker;
import jp.cafebabe.pochi.pairs.SpecifiedPairMatcher;

import java.io.Serializable;
import java.util.Objects;

public class SpecifiedPairMatcherBuilder<T extends Serializable> extends AbstractPairMatcherBuilder<T> {
    private CorrespondenceChecker<T> checker;

    public SpecifiedPairMatcherBuilder() {
        this((item1, item2) -> Objects.equals(item1, item2));
    }

    public SpecifiedPairMatcherBuilder(CorrespondenceChecker<T> checker) {
        super(SpecifiedPairMatcher.TYPE);
        this.checker = checker;
    }

    @Override
    public PairMatcher<T> build(Configuration config) {
        return new SpecifiedPairMatcher<>(config, checker);
    }
}
