package com.github.ebis.scripts.helper;

import com.github.ebis.birthmarks.BirthmarkExtractor;
import com.github.ebis.birthmarks.entities.ExtractionResults;
import com.github.ebis.config.Configuration;
import com.github.kunai.source.DataSource;

public class Extractor {
    private BirthmarkExtractor extractor;
    private Configuration context;

    public Extractor(BirthmarkExtractor extractor, Configuration context){
        this.extractor = extractor;
        this.context = context;
    }

    public ExtractionResults extract(DataSource source){
        return extractor.extract(source, context);
    }
}
