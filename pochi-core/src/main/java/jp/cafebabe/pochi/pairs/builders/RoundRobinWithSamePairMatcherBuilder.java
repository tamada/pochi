package jp.cafebabe.pochi.pairs.builders;

public class RoundRobinWithSamePairMatcherBuilder<T> extends RoundRobinPairMatcherBuilder<T>{
    public RoundRobinWithSamePairMatcherBuilder() {
        super(true);
    }
}
