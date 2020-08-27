package jp.cafebabe.pochi.birthmarks.comparators;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

import io.vavr.control.Either;
import jp.cafebabe.pochi.birthmarks.AbstractTask;
import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.birthmarks.entities.Birthmarks;
import jp.cafebabe.pochi.birthmarks.entities.Percentage;
import jp.cafebabe.pochi.birthmarks.entities.Progress;
import jp.cafebabe.pochi.birthmarks.pairs.Pair;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcher;

public abstract class AbstractComparator extends AbstractTask<ComparatorType> implements Comparator {
    private Consumer<Percentage> notifier = (percentage) -> {};

    public AbstractComparator(ComparatorType type, Configuration config){
        super(type, config);
    }

    public void setProgressNotifier(Consumer<Percentage> notifier) {
        this.notifier = notifier;
    }

    @Override
    public final Either<Exception, Similarity> similarity(Pair<Birthmark> pair){
        try{
            return Either.right(pair.map((left, right) -> calculate(left, right)));
        } catch(Exception e) {
            return Either.left(e);
        }
    }

    @Override
    public Comparisons compare(Birthmarks results, PairMatcher<Birthmark> maker){
        return new Comparisons(buildStream(maker.match(results),
                new Progress(maker.count(results)))
                .filter(either -> either.isRight())
                .map(either -> either.get()));

    }

    @Override
    public Comparisons compare(Birthmarks left, Birthmarks right, PairMatcher<Birthmark> maker){
        return new Comparisons(buildStream(maker.match(left, right), new Progress(maker.count(left, right), notifier))
                .filter(either -> either.isRight())
                .map(either -> either.get()));
    }

    protected abstract Similarity calculate(Birthmark left, Birthmark right);

    private Stream<Either<Exception, Comparison>> buildStream(Stream<Pair<Birthmark>> pairs, Progress progress){
        return pairs.peek(item -> progress.update())
                .map(pair -> compare(pair));
    }
}
