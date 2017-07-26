package com.github.pochi.runner.birthmarks.comparators;

import java.util.List;

import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.Element;
import com.github.pochi.runner.config.Configuration;

public class SimpsonIndexBirthmarkComparator extends IndexBirthmarkComparator {
    public SimpsonIndexBirthmarkComparator(){
        super(new ComparatorType("SimpsonIndex"));
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right, Configuration config){
        List<Element> intersection = intersect(left, right);
        int denominator = Math.min(left.elementCount(), right.elementCount());
        return new Similarity(1.0 * intersection.size() / denominator);
    }
}
