package jp.cafebabe.pochi.pairs.builders;

import java.io.Serializable;

public class RoundRobinWithSamePairMatcherBuilder<T extends Serializable> extends RoundRobinPairMatcherBuilder<T>{
    public RoundRobinWithSamePairMatcherBuilder() {
        super(true);
    }
}
