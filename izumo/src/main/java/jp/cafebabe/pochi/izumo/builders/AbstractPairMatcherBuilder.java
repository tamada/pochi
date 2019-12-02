package jp.cafebabe.pochi.izumo.builders;

import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcher;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcherBuilder;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcherType;

public abstract class AbstractPairMatcherBuilder<T> implements PairMatcherBuilder<T> {
    private PairMatcherType type;

    public AbstractPairMatcherBuilder(PairMatcherType type) {
        this.type = type;
    }

    @Override
    public final PairMatcherType type() {
        return type;
    }

    @Override
    public abstract PairMatcher<T> build(Configuration config);
}
