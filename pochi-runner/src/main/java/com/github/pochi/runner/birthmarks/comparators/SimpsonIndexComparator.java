package com.github.pochi.runner.birthmarks.comparators;

import java.util.List;

import com.github.pochi.birthmarks.comparators.ComparatorType;
import com.github.pochi.birthmarks.comparators.Similarity;
import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.Element;

public class SimpsonIndexComparator extends IndexComparator {
    public SimpsonIndexComparator(Configuration config){
        super(new ComparatorType("SimpsonIndex"), config);
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right){
        List<Element> intersection = intersect(left, right);
        int denominator = Math.min(left.elementCount(), right.elementCount());
        return new Similarity(1.0 * intersection.size() / denominator);
    }
}
