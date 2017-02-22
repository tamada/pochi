package com.github.pochi.runner.scripts.helper;

import java.util.stream.Stream;

import com.github.pochi.kunai.source.DataSource;
import com.github.pochi.runner.birthmarks.BirthmarkExtractor;
import com.github.pochi.runner.birthmarks.DefaultBirthmarkParser;
import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.Birthmarks;
import com.github.pochi.runner.birthmarks.entities.Metadata;
import com.github.pochi.runner.birthmarks.entities.Results;
import com.github.pochi.runner.config.Configuration;

public class Extractor {
    private BirthmarkExtractor extractor;
    private Configuration context;

    public Extractor(BirthmarkExtractor extractor, Configuration context){
        this.extractor = extractor;
        this.context = context;
    }

    public Results<Birthmarks> extract(DataSource source){
        Stream<Birthmark> stream = parse(source);
        return new Results<>(extractor.type(), new Birthmarks(stream));
    }

    private Stream<Birthmark> parse(DataSource source){
        Stream<Birthmark> extractedStream = extractor.extractForStream(source, context);
        Stream<Birthmark> readStream = new DefaultBirthmarkParser().parseForStream(source, context);
        return Stream.concat(extractedStream, readStream);
    }

    public Stream<Metadata> failedSources(){
        return extractor.failedSources();
    }
}
