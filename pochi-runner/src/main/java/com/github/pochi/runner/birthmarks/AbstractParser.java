package com.github.pochi.runner.birthmarks;

import java.util.List;
import java.util.stream.Stream;

import com.github.pochi.birthmarks.AbstractTask;
import com.github.pochi.birthmarks.BirthmarkParser;
import com.github.pochi.birthmarks.ParserType;
import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.Metadata;
import com.github.pochi.kunai.entries.Entry;

public abstract class AbstractParser extends AbstractTask<ParserType> implements BirthmarkParser{
    public AbstractParser(ParserType type, Configuration config) {
        super(type, config);
    }

    public abstract List<Birthmark> parseEntry(Entry entry);

    public abstract Stream<Metadata> failedSources();    
}
