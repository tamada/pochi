package com.github.pochi.runner.scripts.helper;

import java.util.stream.Stream;

import com.github.pochi.kunai.source.DataSource;
import com.github.pochi.runner.birthmarks.BirthmarkParser;
import com.github.pochi.runner.birthmarks.entities.Birthmarks;
import com.github.pochi.runner.birthmarks.entities.Metadata;
import com.github.pochi.runner.config.Configuration;

public class Parser {
    private BirthmarkParser target;
    private Configuration context;

    public Parser(BirthmarkParser parser, Configuration context){
        this.target = parser;
        this.context = context;
    }

    public Birthmarks parse(DataSource source){
        return target.parse(source, context);
    }

    public Stream<Metadata> failedSources(){
        return target.failedSources();
    }
}
