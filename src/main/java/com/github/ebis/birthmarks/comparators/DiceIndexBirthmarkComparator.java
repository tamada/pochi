package com.github.ebis.birthmarks.comparators;

import java.util.List;

import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.birthmarks.entities.Element;
import com.github.ebis.config.Configuration;

public class DiceIndexBirthmarkComparator extends IndexBirthmarkComparator {
    public DiceIndexBirthmarkComparator(){
        super(new ComparatorType("DiceIndex"));
    }

    @Override
    public Similarity compare(Birthmark left, Birthmark right, Configuration config){
        List<Element> intersection = intersect(left, right);
        int denominator = left.elementCount() + right.elementCount();
        return new Similarity(1.0 * intersection.size() / denominator);
    }
}
