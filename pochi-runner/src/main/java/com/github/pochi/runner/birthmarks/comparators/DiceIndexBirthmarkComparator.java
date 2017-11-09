package com.github.pochi.runner.birthmarks.comparators;

import java.util.Set;

import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.Element;
import com.github.pochi.runner.config.Configuration;

public class DiceIndexBirthmarkComparator extends IndexBirthmarkComparator {
    public DiceIndexBirthmarkComparator(){
        super(new ComparatorType("DiceIndex"));
    }

    @Override
    public Similarity similarity(Birthmark left, Birthmark right, Configuration config){
        Set<Element> intersection = intersect(left, right);
        int denominator = left.elementCount() + right.elementCount();
        return new Similarity(2.0 * intersection.size() / denominator);
    }
}
