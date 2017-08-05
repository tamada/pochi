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
import com.github.pochi.birthmarks.extractors.ExtractorBuilder;
import com.github.pochi.birthmarks.pairs.PairMakerBuilder;
import com.github.pochi.birthmarks.pairs.PairMakerType;
import com.github.pochi.birthmarks.rules.Position;
import com.github.pochi.birthmarks.rules.Rule;
import com.github.pochi.birthmarks.rules.Snippet;
import com.github.pochi.runner.birthmarks.BirthmarkSystem;
import com.github.pochi.runner.birthmarks.pairs.PairMakerBuilders;
import com.github.pochi.runner.birthmarks.parsers.DefaultParser;

public class BirthmarkSystemHelper {
    private BirthmarkSystem system;
    private Configuration configuration;
    private PairMakerBuilders pairmakers = new PairMakerBuilders();

    public BirthmarkSystemHelper(Configuration configuration){
        this(new BirthmarkSystem(), configuration);
    }

    public BirthmarkSystemHelper(BirthmarkSystem system, Configuration configuration){
        this.system = system;
        this.configuration = configuration;
    }

    public BirthmarkParser parser(){
        return new DefaultParser(configuration);
    }

    public Threshold threshold(double threshold){
        return new Threshold(threshold);
    }

    public PairMakerBuilder pairMaker(String maker){
        return pairmakers.builder(new PairMakerType(maker));
    }

    public String pairMakers(){
        return pairmakers.availableServices()
                .map(PairMakerType::toString)
                .collect(Collectors.joining(","));
    }

    public Extractor extractor(String birthmarkName){
        ExtractorBuilder extractor = system.extractor(new BirthmarkType(birthmarkName));
        return extractor.build(configuration);
    }

    public Rule rule(String type, String pattern){
        return new Rule(Position.valueOf(type), new Snippet(pattern));
    }

    public Comparator comparator(String type){
        return system.comparator(new ComparatorType(type))
                .build(configuration);
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
