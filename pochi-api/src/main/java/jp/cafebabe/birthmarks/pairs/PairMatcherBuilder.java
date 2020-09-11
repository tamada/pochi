package jp.cafebabe.birthmarks.pairs;

import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.config.Configuration;

public interface PairMatcherBuilder<T> extends TaskBuilder<PairMatcherType> {

    @Override
    public PairMatcherType type();

    @Override
    public PairMatcher<T> build(Configuration config);
}
