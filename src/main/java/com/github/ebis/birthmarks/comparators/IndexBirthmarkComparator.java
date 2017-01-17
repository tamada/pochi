package com.github.ebis.birthmarks.comparators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.birthmarks.entities.Element;

public abstract class IndexBirthmarkComparator extends AbstractBirthmarkComparator {
    public IndexBirthmarkComparator(ComparatorType type){
        super(type);
    }

    Set<Element> union(Birthmark left, Birthmark right){
        Set<Element> union = new HashSet<>();
        left.forEach(item -> union.add(item));
        right.forEach(item -> union.add(item));
        return union;
    }

    List<Element> intersect(Birthmark left, Birthmark right){
        List<Element> intersection = new ArrayList<>();
        right.forEach(item -> left.contains(item),
                item -> intersection.add(item));
        return intersection;
    }
}
