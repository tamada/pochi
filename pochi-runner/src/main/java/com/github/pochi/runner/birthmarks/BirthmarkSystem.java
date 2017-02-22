package com.github.pochi.runner.birthmarks;

import com.github.pochi.runner.birthmarks.comparators.ComparatorType;
import com.github.pochi.runner.birthmarks.entities.BirthmarkType;

public class BirthmarkSystem {
    private BirthmarkExtractors extractors = new BirthmarkExtractors();
    private BirthmarkComparators comparators = new BirthmarkComparators();

    public BirthmarkExtractor extractor(BirthmarkType type){
        return extractors.service(type);
    }

    public BirthmarkType[] availableExtractors(){
        return extractors.availableTypes();
    }

    public BirthmarkComparator comparator(ComparatorType type){
        return comparators.service(type);
    }

    public ComparatorType[] availableComparators(){
        return comparators.availableTypes();
    }
}
