package com.github.ebis.scripts.helper;

import java.util.stream.Stream;

import com.github.ebis.birthmarks.BirthmarkExtractor;
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
        return extractor.extract(source, context);
    }

    public Stream<Metadata> failedSources(){
        return extractor.failedSources();
    }
}
