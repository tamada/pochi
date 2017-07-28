package com.github.pochi.birthmarks.extractors;

import java.util.Optional;
import java.util.stream.Stream;

import javax.security.auth.login.Configuration;

import com.github.pochi.birthmarks.Task;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.BirthmarkType;
import com.github.pochi.birthmarks.entities.Birthmarks;
import com.github.pochi.birthmarks.entities.Metadata;
import com.github.pochi.kunai.entries.Entry;
import com.github.pochi.kunai.source.DataSource;

public interface Extractor extends Task<BirthmarkType>{
    @Override
    BirthmarkType type();

    default Stream<Birthmark> extractForStream(DataSource source, Configuration context){
        Stream<Optional<Birthmark>> stream = source.stream()
                .filter(entry -> entry.endsWith(".class"))
                .map(entry -> extractEach(entry, context));
        return filter(stream);
    }

    default Birthmarks extract(DataSource source, Configuration context){
        return new Birthmarks(extractForStream(source, context));
    }

    Optional<Birthmark> extractEach(Entry entry, Configuration context);

    Stream<Metadata> failedSources();
}
