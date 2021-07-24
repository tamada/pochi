package jp.cafebabe.birthmarks.comparators;

import java.util.function.Consumer;
import java.util.stream.Stream;

import io.vavr.control.Either;
import jp.cafebabe.birthmarks.AbstractTask;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.entities.Percentage;
import jp.cafebabe.birthmarks.entities.Progress;
import jp.cafebabe.birthmarks.entities.Pair;
import jp.cafebabe.birthmarks.pairs.PairMatcher;

public abstract class AbstractComparator extends AbstractTask<ComparatorType> implements Comparator {
    private Consumer<Percentage> notifier = (percentage) -> {};

    public AbstractComparator(ComparatorType type, Configuration config){
        super(type, config);
    }

    public void setProgressNotifier(Consumer<Percentage> notifier) {
        this.notifier = notifier;
    }

    @Override
    public final <T> Either<Exception, Similarity> similarity(Pair<Birthmark<T>> pair){
        try{
            return Either.right(pair.map((left, right) -> calculate(left, right)));
        } catch(Exception e) {
            return Either.left(e);
        }
    }

    @Override
    public <T> Comparisons<T> compare(Birthmarks<T> results, PairMatcher<Birthmark<T>> maker){
        return new Comparisons<>(buildStream(maker.match(results),
                new Progress(maker.count(results)))
                .filter(either -> either.isRight())
                .map(either -> either.get()));

    }

    @Override
    public <T> Comparisons<T> compare(Birthmarks<T> left, Birthmarks<T> right, PairMatcher<Birthmark<T>> maker){
        return new Comparisons<>(buildStream(maker.match(left, right), new Progress(maker.count(left, right), notifier))
                .filter(either -> either.isRight())
                .map(either -> either.get()));
    }

    protected abstract <T> Similarity calculate(Birthmark<T> left, Birthmark<T> right);

    private <T> Stream<Either<Exception, Comparison<T>>> buildStream(Stream<Pair<Birthmark<T>>> pairs, Progress progress){
        return pairs.peek(item -> progress.update())
                .map(pair -> compare(pair));
    }
}
