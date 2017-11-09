package com.github.pochi.runner.birthmarks.comparators;

import java.util.Set;

import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.Element;
import com.github.pochi.runner.config.Configuration;

public class JaccardIndexBirthmarkComparator extends IndexBirthmarkComparator {
    public JaccardIndexBirthmarkComparator(){
        super(new ComparatorType("JaccardIndex"));
    }

    @Override
    public Similarity similarity(Birthmark left, Birthmark right, Configuration config){
        Set<Element> intersection = intersect(left, right);
        Set<Element> union = union(left, right);
        return new Similarity(1.0 * intersection.size() / union.size());
    }
}
