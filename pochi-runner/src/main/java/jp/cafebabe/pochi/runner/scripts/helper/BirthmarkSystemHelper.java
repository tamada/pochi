package jp.cafebabe.pochi.runner.scripts.helper;

import java.util.Arrays;
import java.util.stream.Collectors;

import jp.cafebabe.pochi.birthmarks.BirthmarkParser;
import jp.cafebabe.pochi.birthmarks.comparators.Comparator;
import jp.cafebabe.pochi.birthmarks.comparators.ComparatorType;
import jp.cafebabe.pochi.birthmarks.comparators.Threshold;
import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.birthmarks.extractors.Extractor;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcher;
import jp.cafebabe.pochi.birthmarks.pairs.PairMatcherType;
import jp.cafebabe.pochi.birthmarks.rules.Position;
import jp.cafebabe.pochi.birthmarks.rules.Rule;
import jp.cafebabe.pochi.birthmarks.rules.Snippet;
import jp.cafebabe.pochi.runner.birthmarks.BirthmarkSystem;
import jp.cafebabe.pochi.runner.birthmarks.pairs.PairMatcherBuilders;
import jp.cafebabe.pochi.runner.birthmarks.parsers.DefaultParser;

public class BirthmarkSystemHelper {
    private BirthmarkSystem system;
    private PairMatcherBuilders pairMatchers = new PairMatcherBuilders();

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

    public PairMatcher<Birthmark> pairMatcher(String maker, Configuration config){
        return pairMatchers.builder(new PairMatcherType(maker))
                .build(config);
    }

    public String pairMatchers(){
        return pairMatchers.availableTypes()
                .map(PairMatcherType::toString)
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
