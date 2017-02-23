package com.github.pochi.runner.scripts.helper;

import java.util.stream.Stream;

import com.github.pochi.kunai.source.DataSource;
import com.github.pochi.runner.birthmarks.BirthmarkExtractor;
import com.github.pochi.runner.birthmarks.DefaultBirthmarkParser;
import com.github.pochi.runner.birthmarks.entities.Birthmarks;
import com.github.pochi.runner.birthmarks.entities.Metadata;
import com.github.pochi.runner.config.Configuration;

public class Extractor {
    private BirthmarkExtractor extractor;
    private Configuration context;

    public Extractor(BirthmarkExtractor extractor, Configuration context){
        this.extractor = extractor;
        this.context = context;
    }

    public Birthmarks extract(DataSource source){
        return parse(source);
    }

    private Birthmarks parse(DataSource source){
        Birthmarks extractedStream = extractor.extract(source, context);
        Birthmarks readStream = new DefaultBirthmarkParser().parse(source, context);
        return Birthmarks.merge(extractedStream, readStream);
    }

    public Stream<Metadata> failedSources(){
        return extractor.failedSources();
    }
}
