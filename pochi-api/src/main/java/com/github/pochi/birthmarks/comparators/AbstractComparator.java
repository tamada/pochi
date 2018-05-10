package com.github.pochi.birthmarks.comparators;

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
    public final Similarity similarity(Pair<Birthmark> pair){
        return calculate(pair.left(), 
                pair.right());
    }

    @Override
    public Comparisons compare(Birthmarks results, PairMatcher maker, BiConsumer<Birthmark, Birthmark> action){
        return new Comparisons(compareWith(results, maker, action));
    }

    protected abstract Similarity calculate(Birthmark left, Birthmark right);

    private Stream<Comparison> compareWith(Birthmarks extractedBirthmarks, PairMatcher maker, BiConsumer<Birthmark, Birthmark> action){
        return maker.match(extractedBirthmarks)
                .map(pair -> compare(pair, action));
    }
}
