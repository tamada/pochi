package com.github.pochi.runner.scripts.helper;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.github.pochi.runner.birthmarks.BirthmarkExtractor;
import com.github.pochi.runner.birthmarks.BirthmarkSystem;
import com.github.pochi.runner.birthmarks.DefaultBirthmarkParser;
import com.github.pochi.runner.birthmarks.PairMaker;
import com.github.pochi.runner.birthmarks.comparators.ComparatorType;
import com.github.pochi.runner.birthmarks.comparators.Threshold;
import com.github.pochi.runner.birthmarks.entities.BirthmarkType;
import com.github.pochi.runner.birthmarks.entities.PairMakerType;
import com.github.pochi.runner.birthmarks.pairs.PairMakers;
import com.github.pochi.runner.birthmarks.rules.Position;
import com.github.pochi.runner.birthmarks.rules.Rule;
import com.github.pochi.runner.birthmarks.rules.Snippet;
import com.github.pochi.runner.config.Configuration;

public class BirthmarkSystemHelper {
    private BirthmarkSystem system;
    private Configuration configuration;
    private PairMakers pairmakers = new PairMakers();

    public BirthmarkSystemHelper(Configuration configuration){
        this(new BirthmarkSystem(), configuration);
    }

    public BirthmarkSystemHelper(BirthmarkSystem system, Configuration configuration){
        this.system = system;
        this.configuration = configuration;
    }

    public Parser parser(){
        return new Parser(new DefaultBirthmarkParser(), configuration);
    }

    public Threshold threshold(double threshold){
        return new Threshold(threshold);
    }

    public PairMaker pairMaker(String maker){
        return pairmakers.service(new PairMakerType(maker));
    }

    public String pairMakers(){
        return pairmakers.availableServices()
                .map(type -> type.toString())
                .collect(Collectors.joining(","));
    }

    public Extractor extractor(String birthmarkName){
        BirthmarkExtractor extractor = system.extractor(new BirthmarkType(birthmarkName));
        return new Extractor(extractor, configuration);
    }

    public Rule rule(String type, String pattern){
        return new Rule(Position.valueOf(type), new Snippet(pattern));
    }

    public Comparator comparator(String type){
        return new Comparator(system.comparator(new ComparatorType(type)), configuration);
    }

    public String comparators(){
        return Arrays.stream(system.availableComparators())
                .map(type -> type.toString())
                .collect(Collectors.joining(","));
    }

    public String extractors(){
        return Arrays.stream(system.availableExtractors())
                .map(type -> type.toString())
                .collect(Collectors.joining(","));
    }

    public String rules(){
        return Arrays.stream(configuration.rules())
                .map(rule -> rule.toString())
                .collect(Collectors.joining(","));
    }
}
