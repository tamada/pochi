package com.github.pochi.runner.birthmarks.comparators;

import java.util.Objects;
import java.util.stream.Stream;

import com.github.pochi.runner.birthmarks.BirthmarkComparator;
import com.github.pochi.runner.birthmarks.PairMaker;
import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.Birthmarks;
import com.github.pochi.runner.birthmarks.entities.Pair;
import com.github.pochi.runner.config.Configuration;

public abstract class AbstractBirthmarkComparator implements BirthmarkComparator{
    private ComparatorType type;

    public AbstractBirthmarkComparator(ComparatorType type){
        this.type = type;
    }

    @Override
    public Comparisons compare(Birthmarks results, PairMaker maker, Configuration config){
        return new Comparisons(compareWith(results, maker, config));
    }

    private Stream<Comparison> compareWith(Birthmarks extractedBirthmarks, PairMaker maker, Configuration config){
        return maker.pairUpWith(extractedBirthmarks)
                .map(pair -> compare(pair, config));
    }

    public boolean is(ComparatorType otherType){
        return Objects.equals(type, otherType);
    }

    @Override
    public final Similarity similarity(Pair<Birthmark> pair, Configuration config){
        return calculate(pair.left(), 
                pair.right(), config);
    }

    protected abstract Similarity calculate(Birthmark left, Birthmark right, Configuration config);

    @Override
    public ComparatorType type(){
        return type;
    }
}
