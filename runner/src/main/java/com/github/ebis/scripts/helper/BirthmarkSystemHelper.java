package com.github.ebis.scripts.helper;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.github.ebis.birthmarks.BirthmarkExtractor;
import com.github.ebis.birthmarks.BirthmarkSystem;
import com.github.ebis.birthmarks.DefaultBirthmarkParser;
import com.github.ebis.birthmarks.PairMaker;
import com.github.ebis.birthmarks.comparators.ComparatorType;
import com.github.ebis.birthmarks.entities.BirthmarkType;
import com.github.ebis.birthmarks.entities.PairMakerType;
import com.github.ebis.birthmarks.pairs.PairMakers;
import com.github.ebis.birthmarks.rules.Position;
import com.github.ebis.birthmarks.rules.Rule;
import com.github.ebis.birthmarks.rules.Snippet;
import com.github.ebis.config.Configuration;

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
