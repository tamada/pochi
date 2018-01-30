package com.github.pochi.runner.birthmarks;

import com.github.pochi.birthmarks.comparators.ComparatorBuilder;
import com.github.pochi.birthmarks.comparators.ComparatorType;
import com.github.pochi.birthmarks.entities.BirthmarkType;
import com.github.pochi.birthmarks.extractors.ExtractorBuilder;
import com.github.pochi.runner.birthmarks.comparators.ComparatorBuilders;
import com.github.pochi.runner.birthmarks.extractors.ExtractorBuilders;

public class BirthmarkSystem {
    private ExtractorBuilders extractors = new ExtractorBuilders();
    private ComparatorBuilders comparators = new ComparatorBuilders();

    public ExtractorBuilder extractor(BirthmarkType type){
        return extractors.builder(type);
    }

    public BirthmarkType[] availableExtractors(){
        return extractors.availableServices()
                .toArray(BirthmarkType[]::new);
    }

    public ComparatorBuilder comparator(ComparatorType type){
        return comparators.builder(type);
    }

    public ComparatorType[] availableComparators(){
        return comparators.availableServices()
                .toArray(ComparatorType[]::new);
    }
}
