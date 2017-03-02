package com.github.pochi.runner.birthmarks;

import java.util.stream.Stream;

import com.github.pochi.kunai.entries.Entry;
import com.github.pochi.kunai.source.DataSource;
import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.BirthmarkType;
import com.github.pochi.runner.birthmarks.entities.Birthmarks;
import com.github.pochi.runner.birthmarks.entities.Elements;
import com.github.pochi.runner.birthmarks.entities.Metadata;
import com.github.pochi.runner.config.Configuration;
import com.github.pochi.runner.util.TimeredList;

public interface BirthmarkParser extends Service<BirthmarkType> {
    @Override
    BirthmarkType type();

    TimeredList<Birthmark> parseForStream(DataSource source, Configuration context);

    Birthmarks parse(DataSource source, Configuration context);

    TimeredList<Birthmark> parseEntry(Entry entry, Configuration context);

    Elements buildElements(Stream<String> source);

    Stream<Metadata> failedSources();    
}
