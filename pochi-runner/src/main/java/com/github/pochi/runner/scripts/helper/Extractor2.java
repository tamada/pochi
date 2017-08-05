package com.github.pochi.runner.scripts.helper;

import java.util.stream.Stream;

import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.Birthmarks;
import com.github.pochi.birthmarks.entities.Metadata;
import com.github.pochi.birthmarks.extractors.Extractor;
import com.github.pochi.kunai.source.DataSource;
import com.github.pochi.runner.birthmarks.parsers.DefaultParser;

public class Extractor2 {
    private Extractor target;
    private Configuration context;

    public Extractor2(Extractor extractor, Configuration context){
        this.target = extractor;
        this.context = context;
    }

    public Birthmarks extract(DataSource source){
        return parse(source);
    }

    private Birthmarks parse(DataSource source){
        Birthmarks extractedStream = target.extract(source);
        Birthmarks readStream = new DefaultParser(context).parse(source);
        return extractedStream.merge(readStream);
    }

    public Stream<Metadata> failedSources(){
        return target.failedSources();
    }
}
