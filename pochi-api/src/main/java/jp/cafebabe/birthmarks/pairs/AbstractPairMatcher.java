package jp.cafebabe.birthmarks.pairs;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.AbstractTask;

import java.io.Serializable;

public abstract class AbstractPairMatcher<T extends Serializable> extends AbstractTask<PairMatcherType> implements PairMatcher<T> {
    public AbstractPairMatcher(PairMatcherType type, Configuration config){
        super(type, config);
    }
}
