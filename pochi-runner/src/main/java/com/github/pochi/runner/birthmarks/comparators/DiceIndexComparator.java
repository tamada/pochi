package com.github.pochi.runner.birthmarks.comparators;

import java.util.List;

import com.github.pochi.birthmarks.comparators.ComparatorType;
import com.github.pochi.birthmarks.comparators.Similarity;
import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.Element;

public class DiceIndexComparator extends IndexComparator {
    public DiceIndexComparator(Configuration config){
        super(new ComparatorType("DiceIndex"), config);
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        List<Element> intersection = intersect(left, right);
        int denominator = left.elementCount() + right.elementCount();
        return new Similarity(2.0 * intersection.size() / denominator);
    }
}
