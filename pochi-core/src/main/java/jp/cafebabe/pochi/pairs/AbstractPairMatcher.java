package jp.cafebabe.pochi.pairs;

import jp.cafebabe.birthmarks.AbstractTask;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairs.PairMatcher;
import jp.cafebabe.birthmarks.pairs.PairMatcherType;

public abstract class AbstractPairMatcher<T> extends AbstractTask<PairMatcherType> implements PairMatcher<T>{
    public AbstractPairMatcher(PairMatcherType type, Configuration config) {
        super(type, config);
    }
}
