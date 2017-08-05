package com.github.pochi.runner.scripts.helper;

import com.github.pochi.birthmarks.comparators.Comparator;
import com.github.pochi.birthmarks.comparators.Comparisons;
import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.Birthmarks;
import com.github.pochi.birthmarks.pairs.PairMaker;

public class Comparator2 {
    private Comparator target;

    public Comparator2(Comparator comparator, Configuration config){
        this.target = comparator;
    }

    public Comparisons compare(Birthmarks birthmarks, PairMaker maker){
        return target.compare(birthmarks, maker);
    }
}
