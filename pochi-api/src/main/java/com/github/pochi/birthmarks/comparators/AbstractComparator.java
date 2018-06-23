package com.github.pochi.birthmarks.comparators;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import com.github.pochi.birthmarks.AbstractTask;
import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.Birthmarks;
import com.github.pochi.birthmarks.pairs.Pair;
import com.github.pochi.birthmarks.pairs.PairMatcher;

public abstract class AbstractComparator extends AbstractTask<ComparatorType> implements Comparator {
    public AbstractComparator(ComparatorType type, Configuration config){
        super(type, config);
    }

    @Override
    public final Optional<Similarity> similarity(Pair<Birthmark> pair, BiConsumer<Pair<Birthmark>, Exception> callback){
        try{
            return Optional.of(calculate(pair.left(), pair.right()));
        } catch(Exception e) {
            callback.accept(pair, e);
        }
        return Optional.empty();
    }

    @Override
    public Comparisons compare(Birthmarks results, PairMatcher<Birthmark> maker,
            BiConsumer<Pair<Birthmark>, Exception> callback){
        return new Comparisons(buildStream(maker.match(results), callback));
    }

    @Override
    public Comparisons compare(Birthmarks left, Birthmarks right, PairMatcher<Birthmark> maker,
            BiConsumer<Pair<Birthmark>, Exception> callback){
        return new Comparisons(buildStream(maker.match(left, right), callback));
    }

    protected abstract Similarity calculate(Birthmark left, Birthmark right);

    private Stream<Comparison> buildStream(Stream<Pair<Birthmark>> pairs,
            BiConsumer<Pair<Birthmark>, Exception> callback){
        return pairs.map(pair -> compare(pair, callback))
                .filter(optional -> optional.isPresent())
                .map(optional -> optional.get());
        
    }
}
