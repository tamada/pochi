package jp.cafebabe.pochi.birthmarks.pairs;

import jp.cafebabe.pochi.birthmarks.AbstractTask;
import jp.cafebabe.pochi.birthmarks.config.Configuration;

public abstract class AbstractPairMatcher<T> extends AbstractTask<PairMatcherType> implements PairMatcher<T> {
    public AbstractPairMatcher(PairMatcherType type, Configuration config){
        super(type, config);
    }
}
