package com.github.ebis.scripts.helper;

import java.util.stream.Stream;

import com.github.ebis.birthmarks.BirthmarkExtractor;
import com.github.ebis.birthmarks.DefaultBirthmarkParser;
import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.birthmarks.entities.Birthmarks;
import com.github.ebis.birthmarks.entities.Metadata;
import com.github.ebis.birthmarks.entities.Results;
import com.github.ebis.config.Configuration;
import com.github.kunai.source.DataSource;

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
