package com.github.pochi.runner.scripts.helper;

import com.github.pochi.runner.birthmarks.BirthmarkComparator;
import com.github.pochi.runner.birthmarks.PairMaker;
import com.github.pochi.runner.birthmarks.comparators.Comparisons;
import com.github.pochi.runner.birthmarks.entities.Birthmarks;
import com.github.pochi.runner.config.Configuration;

public class Comparator {
    private BirthmarkComparator target;
    private Configuration config;

    public Comparator(BirthmarkComparator comparator, Configuration config){
        this.target = comparator;
        this.config = config;
    }

    public Comparisons compare(Birthmarks birthmarks, PairMaker maker){
        return target.compare(birthmarks, maker, config);
    }

    public Comparisons compare(Birthmarks birthmarks1, Birthmarks birthmarks2, PairMaker maker){
        return target.compare(birthmarks1, birthmarks2, maker, config);
    }
}
