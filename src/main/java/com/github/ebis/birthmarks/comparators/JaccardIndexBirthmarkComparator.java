package com.github.ebis.birthmarks.comparators;

import java.util.List;
import java.util.Set;

import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.birthmarks.entities.Element;
import com.github.ebis.config.Configuration;

public class JaccardIndexBirthmarkComparator extends IndexBirthmarkComparator {
    public JaccardIndexBirthmarkComparator(){
        super(new ComparatorType("JaccardIndex"));
    }

    @Override
    public Similarity compare(Birthmark left, Birthmark right, Configuration config){
        List<Element> intersection = intersect(left, right);
        Set<Element> union = union(left, right);
        return new Similarity(1.0 * intersection.size() / union.size());
    }
}
