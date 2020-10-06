package jp.cafebabe.pochi.pairs;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import jp.cafebabe.birthmarks.TaskBuilders;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.pairs.PairMatcherBuilder;
import jp.cafebabe.birthmarks.pairs.PairMatcherType;
import jp.cafebabe.pochi.pairs.builders.GuessedPairMatcherBuilder;
import jp.cafebabe.pochi.pairs.builders.RoundRobinPairMatcherBuilder;

public class PairMatcherBuilders implements TaskBuilders<PairMatcherType, PairMatcherBuilder<Birthmark>>{
    private Map<PairMatcherType, PairMatcherBuilder<Birthmark>> map = new HashMap<>();

    public PairMatcherBuilders() {
        registerBuilders();
    }

    private void registerBuilders() {
        map.put(GuessedPairMatcher.TYPE, new GuessedPairMatcherBuilder<Birthmark>());
        map.put(RoundRobinPairMatcher.SAME_PAIR_TYPE, new RoundRobinPairMatcherBuilder<Birthmark>(true));
        map.put(RoundRobinPairMatcher.TYPE, new RoundRobinPairMatcherBuilder<Birthmark>(false));
    }

    public void register(PairMatcherBuilder<Birthmark> builder) {
        map.put(builder.type(), builder);
    }

    @Override
    public PairMatcherBuilder<Birthmark> builder(PairMatcherType type) {
        return map.get(type);
    }

    @Override
    public boolean available(PairMatcherType type) {
        return map.containsKey(type);
    }

    @Override
    public Stream<PairMatcherType> availableTypes() {
        return map.keySet().stream();
    }
}
