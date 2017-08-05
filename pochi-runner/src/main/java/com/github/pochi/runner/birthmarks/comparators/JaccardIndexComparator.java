package com.github.pochi.runner.birthmarks.comparators;

import java.util.List;
import java.util.Set;

import com.github.pochi.birthmarks.comparators.ComparatorType;
import com.github.pochi.birthmarks.comparators.Similarity;
import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.Element;

public class JaccardIndexComparator extends IndexComparator {
    public JaccardIndexComparator(Configuration config){
        super(new ComparatorType("JaccardIndex"), config);
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right){
        List<Element> intersection = intersect(left, right);
        Set<Element> union = union(left, right);
        return new Similarity(1.0 * intersection.size() / union.size());
    }
}
