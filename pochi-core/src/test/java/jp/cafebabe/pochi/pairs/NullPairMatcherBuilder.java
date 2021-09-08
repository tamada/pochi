package jp.cafebabe.pochi.pairs;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Pair;
import jp.cafebabe.birthmarks.pairs.PairMatcher;
import jp.cafebabe.birthmarks.pairs.PairMatcherType;
import jp.cafebabe.birthmarks.pairs.Streamable;
import jp.cafebabe.pochi.pairs.builders.AbstractPairMatcherBuilder;

import java.util.stream.Stream;

public class NullPairMatcherBuilder extends AbstractPairMatcherBuilder<Birthmark<?>> {
    public static PairMatcherType TYPE = new PairMatcherType("null");

    public NullPairMatcherBuilder() {
        super(TYPE);
    }

    @Override
    public PairMatcher<Birthmark<?>> build(Configuration config) {
        return new PairMatcher<Birthmark<?>>() {
            @Override
            public PairMatcherType type() {
                return TYPE;
            }

            @Override
            public Stream<Pair<Birthmark<?>>> match(Streamable<Birthmark<?>> birthmarks) {
                return Stream.empty();
            }

            @Override
            public Stream<Pair<Birthmark<?>>> match(Streamable<Birthmark<?>> birthmarks1, Streamable<Birthmark<?>> birthmarks2) {
                return Stream.empty();
            }

            @Override
            public long count(Streamable<Birthmark<?>> birthmarks) {
                return 0L;
            }

            @Override
            public long count(Streamable<Birthmark<?>> birthmarks1, Streamable<Birthmark<?>> birthmarks2) {
                return 0L;
            }

            @Override
            public Configuration configuration() {
                return config;
            }
        };
    }
}
