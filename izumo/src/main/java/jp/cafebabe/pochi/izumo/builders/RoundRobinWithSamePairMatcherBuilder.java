package jp.cafebabe.pochi.izumo.builders;

public class RoundRobinWithSamePairMatcherBuilder<T> extends RoundRobinPairMatcherBuilder<T>{
    public RoundRobinWithSamePairMatcherBuilder() {
        super(true);
    }
}
