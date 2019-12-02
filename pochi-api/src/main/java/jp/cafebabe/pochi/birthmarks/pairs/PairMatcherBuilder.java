package jp.cafebabe.pochi.birthmarks.pairs;

import jp.cafebabe.pochi.birthmarks.TaskBuilder;
import jp.cafebabe.pochi.birthmarks.config.Configuration;

public interface PairMatcherBuilder<T> extends TaskBuilder<PairMatcherType>{

    @Override
    public PairMatcherType type();

    @Override
    public PairMatcher<T> build(Configuration config);
}
