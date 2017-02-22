package com.github.pochi.runner.scripts.helper;

import java.util.stream.Stream;

import com.github.pochi.kunai.source.DataSource;
import com.github.pochi.runner.birthmarks.BirthmarkParser;
import com.github.pochi.runner.birthmarks.entities.Birthmarks;
import com.github.pochi.runner.birthmarks.entities.Metadata;
import com.github.pochi.runner.birthmarks.entities.Results;
import com.github.pochi.runner.config.Configuration;

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
