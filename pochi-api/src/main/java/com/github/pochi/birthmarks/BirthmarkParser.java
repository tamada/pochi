package com.github.pochi.birthmarks;

import java.util.List;
import java.util.stream.Stream;

import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.BirthmarkType;
import com.github.pochi.birthmarks.entities.Birthmarks;
import com.github.pochi.birthmarks.entities.Elements;
import com.github.pochi.birthmarks.entities.Metadata;
import com.github.pochi.kunai.entries.Entry;
import com.github.pochi.kunai.source.DataSource;

public interface BirthmarkParser extends Task<BirthmarkType> {
    @Override
    BirthmarkType type();

    List<Birthmark> parseForStream(DataSource source);

    Birthmarks parse(DataSource source);

    List<Birthmark> parseEntry(Entry entry);

    Elements buildElements(Stream<String> source);

    Stream<Metadata> failedSources();    
}
