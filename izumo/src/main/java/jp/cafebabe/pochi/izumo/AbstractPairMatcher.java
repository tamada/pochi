package jp.cafebabe.pochi.izumo;

import jp.cafebabe.pochi.birthmarks.AbstractTask;
import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcher;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcherType;

public abstract class AbstractPairMatcher<T> extends AbstractTask<PairMatcherType> implements PairMatcher<T>{
    public AbstractPairMatcher(PairMatcherType type, Configuration config) {
        super(type, config);
    }
}
