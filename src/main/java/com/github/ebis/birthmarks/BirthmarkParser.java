package com.github.ebis.birthmarks;

import java.util.stream.Stream;

import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.birthmarks.entities.BirthmarkType;
import com.github.ebis.birthmarks.entities.Birthmarks;
import com.github.ebis.birthmarks.entities.Metadata;
import com.github.ebis.birthmarks.entities.Results;
import com.github.ebis.config.Configuration;
import com.github.kunai.entries.Entry;
import com.github.kunai.source.DataSource;

public interface BirthmarkParser extends Service<BirthmarkType> {
    BirthmarkType type();

    default Stream<Birthmark> parseForStream(DataSource source, Configuration context){
        return source.stream()
                .filter(entry -> entry.endsWith(".csv"))
                .flatMap(entry -> parseEntry(entry, context));
    }

    default Results<Birthmarks> parse(DataSource source, Configuration context){
        Stream<Birthmark> stream = parseForStream(source, context);
        // do termination operation before calling ```type()``` 
        Birthmarks birthmarks = new Birthmarks(stream);
        return new Results<>(type(), birthmarks);
    }

    Stream<Birthmark> parseEntry(Entry entry, Configuration context);

    Stream<Metadata> failedSources();    
}
