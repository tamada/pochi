package com.github.pochi.runner.scripts.helper;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.github.pochi.birthmarks.BirthmarkParser;
import com.github.pochi.birthmarks.comparators.Comparator;
import com.github.pochi.birthmarks.comparators.ComparatorType;
import com.github.pochi.birthmarks.comparators.Threshold;
import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.BirthmarkType;
import com.github.pochi.birthmarks.extractors.Extractor;
import com.github.pochi.birthmarks.pairs.PairMaker;
import com.github.pochi.birthmarks.pairs.PairMakerType;
import com.github.pochi.birthmarks.rules.Position;
import com.github.pochi.birthmarks.rules.Rule;
import com.github.pochi.birthmarks.rules.Snippet;
import com.github.pochi.runner.birthmarks.BirthmarkSystem;
import com.github.pochi.runner.birthmarks.pairs.PairMakerBuilders;
import com.github.pochi.runner.birthmarks.parsers.DefaultParser;

public class BirthmarkSystemHelper {
    private BirthmarkSystem system;
    private PairMakerBuilders pairmakers = new PairMakerBuilders();

    public BirthmarkSystemHelper(){
        this(new BirthmarkSystem());
    }

    public BirthmarkSystemHelper(BirthmarkSystem system){
        this.system = system;
    }

    public BirthmarkParser parser(Configuration config){
        return new DefaultParser(config);
    }

    public Threshold threshold(double threshold){
        return new Threshold(threshold);
    }

    public PairMaker pairMaker(String maker, Configuration config){
        return pairmakers.builder(new PairMakerType(maker))
                .build(config);
    }

    public String pairMakers(){
        return pairmakers.availableServices()
                .map(PairMakerType::toString)
                .collect(Collectors.joining(","));
    }

    public Extractor extractor(String birthmarkName, Configuration config){
        return system.extractor(new BirthmarkType(birthmarkName))
                .build(config);
    }

    public Rule rule(String type, String pattern){
        return new Rule(Position.valueOf(type), new Snippet(pattern));
    }

    public Comparator comparator(String type, Configuration config){
        return system.comparator(new ComparatorType(type))
                .build(config);
    }

    public String comparators(){
        return Arrays.stream(system.availableComparators())
                .map(ComparatorType::toString)
                .collect(Collectors.joining(","));
    }

    public String extractors(){
        return Arrays.stream(system.availableExtractors())
                .map(BirthmarkType::toString)
                .collect(Collectors.joining(","));
    }
}
