package com.github.ebis.scripts.helper;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.github.ebis.birthmarks.BirthmarkExtractor;
import com.github.ebis.birthmarks.BirthmarkSystem;
import com.github.ebis.birthmarks.entities.BirthmarkType;
import com.github.ebis.birthmarks.rules.Position;
import com.github.ebis.birthmarks.rules.Rule;
import com.github.ebis.birthmarks.rules.Snippet;
import com.github.ebis.config.Configuration;

public class BirthmarkSystemHelper {
    private BirthmarkSystem system;
    private Configuration configuration;

    public BirthmarkSystemHelper(Configuration configuration){
        this(new BirthmarkSystem(), configuration);
    }

    public BirthmarkSystemHelper(BirthmarkSystem system, Configuration configuration){
        this.system = system;
        this.configuration = configuration;
    }

    public Extractor extractor(String birthmarkName){
        BirthmarkExtractor extractor = system.extractor(new BirthmarkType(birthmarkName));
        return new Extractor(extractor, configuration);
    }

    public Rule rule(String type, String pattern){
        return new Rule(Position.valueOf(type), new Snippet(pattern));
    }

    public String extractors(){
        return Arrays.stream(system.availableExtractors())
                .map(type -> type.toString())
                .collect(Collectors.joining());
    }

    public String rules(){
        return Arrays.stream(configuration.rules())
                .map(rule -> rule.toString())
                .collect(Collectors.joining());
    }
}
