package com.github.ebis.scripts.helper;

import com.github.ebis.birthmarks.BirthmarkExtractor;
import com.github.ebis.birthmarks.entities.BirthmarksSet;
import com.github.ebis.config.Configuration;
import com.github.kunai.source.DataSource;

public class Extractor {
    private BirthmarkExtractor extractor;
    private Configuration context;

    public Extractor(BirthmarkExtractor extractor, Configuration context){
        this.extractor = extractor;
        this.context = context;
    }

    public BirthmarksSet extract(DataSource source){
        return extractor.extract(source, context);
    }
}
