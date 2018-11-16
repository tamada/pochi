package jp.cafebabe.pochi.birthmarks.comparators;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import jp.cafebabe.pochi.birthmarks.AbstractTask;
import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.birthmarks.entities.Birthmarks;
import jp.cafebabe.pochi.birthmarks.entities.Progress;
import jp.cafebabe.pochi.birthmarks.pairs.Pair;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcher;

public abstract class AbstractComparator extends AbstractTask<ComparatorType> implements Comparator {
    public AbstractComparator(ComparatorType type, Configuration config){
        super(type, config);
    }

    @Override
    public final Optional<Similarity> similarity(Pair<Birthmark> pair, BiConsumer<Pair<Birthmark>, Exception> callback){
        try{
            return Optional.of(pair.map((left, right) -> calculate(left, right)));
        } catch(Exception e) {
            callback.accept(pair, e);
        }
        return Optional.empty();
    }

    @Override
    public Comparisons compare(Birthmarks results, PairMatcher<Birthmark> maker,
            BiConsumer<Pair<Birthmark>, Exception> callback){
        return new Comparisons(buildStream(maker.match(results),
                new Progress(maker.count(results)), callback));
    }

    @Override
    public Comparisons compare(Birthmarks left, Birthmarks right, PairMatcher<Birthmark> maker,
            BiConsumer<Pair<Birthmark>, Exception> callback){
        return new Comparisons(buildStream(maker.match(left, right),
                new Progress(maker.count(left, right)),
                callback));
    }

    protected abstract Similarity calculate(Birthmark left, Birthmark right);

    private Stream<Comparison> buildStream(Stream<Pair<Birthmark>> pairs,
            Progress progress, BiConsumer<Pair<Birthmark>, Exception> callback){
        return filter(pairs.peek(item -> progress.update())
                .map(pair -> compare(pair, callback)));
    }
}
