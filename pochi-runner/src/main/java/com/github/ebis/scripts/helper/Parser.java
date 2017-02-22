package com.github.ebis.scripts.helper;

import java.util.stream.Stream;

import com.github.ebis.birthmarks.BirthmarkParser;
import com.github.ebis.birthmarks.entities.Birthmarks;
import com.github.ebis.birthmarks.entities.Metadata;
import com.github.ebis.birthmarks.entities.Results;
import com.github.ebis.config.Configuration;
import com.github.kunai.source.DataSource;

public class Parser {
    private BirthmarkParser parser;
    private Configuration context;

    public Parser(BirthmarkParser parser, Configuration context){
        this.parser = parser;
        this.context = context;
    }

    public Results<Birthmarks> parse(DataSource source){
        return parser.parse(source, context);
    }

    public Stream<Metadata> failedSources(){
        return parser.failedSources();
    }
}
