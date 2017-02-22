package com.github.ebis.scripts.helper;

import com.github.ebis.birthmarks.BirthmarkComparator;
import com.github.ebis.birthmarks.PairMaker;
import com.github.ebis.birthmarks.comparators.Comparisons;
import com.github.ebis.birthmarks.entities.Birthmarks;
import com.github.ebis.birthmarks.entities.Results;
import com.github.ebis.config.Configuration;

public class Comparator {
    private BirthmarkComparator comparator;
    private Configuration config;

    public Comparator(BirthmarkComparator comparator, Configuration config){
        this.comparator = comparator;
        this.config = config;
    }

    public Results<Comparisons> compare(Results<Birthmarks> results, PairMaker maker){
        return comparator.compare(results, maker, config);
    }
}
