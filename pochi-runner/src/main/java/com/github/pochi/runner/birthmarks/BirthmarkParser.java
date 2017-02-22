package com.github.pochi.runner.birthmarks;

import java.util.stream.Stream;

import com.github.pochi.kunai.entries.Entry;
import com.github.pochi.kunai.source.DataSource;
import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.BirthmarkType;
import com.github.pochi.runner.birthmarks.entities.Birthmarks;
import com.github.pochi.runner.birthmarks.entities.Metadata;
import com.github.pochi.runner.birthmarks.entities.Results;
import com.github.pochi.runner.config.Configuration;

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
