package com.github.pochi.runner.birthmarks.comparators;

import java.util.Objects;
import java.util.stream.Stream;

import com.github.pochi.runner.birthmarks.BirthmarkComparator;
import com.github.pochi.runner.birthmarks.PairMaker;
import com.github.pochi.runner.birthmarks.entities.Birthmarks;
import com.github.pochi.runner.birthmarks.entities.Results;
import com.github.pochi.runner.config.Configuration;

public abstract class AbstractBirthmarkComparator implements BirthmarkComparator{
    private ComparatorType type;

    public AbstractBirthmarkComparator(ComparatorType type){
        this.type = type;
    }

    public Results<Comparisons> compare(Results<Birthmarks> results, PairMaker maker, Configuration config){
        Comparisons comparisons = new Comparisons(compareWith(results, maker, config));
        return new Results<>(results.type(), comparisons);
    }

    private Stream<Comparison> compareWith(Results<Birthmarks> results, PairMaker maker, Configuration config){
        return maker.pairUpWith(results.result())
                .map(pair -> new Comparison(pair, compare(pair, config)));
    }

    @Override
    public ComparatorType type(){
        return type;
    }

    public boolean is(ComparatorType otherType){
        return Objects.equals(type, otherType);
    }
}
