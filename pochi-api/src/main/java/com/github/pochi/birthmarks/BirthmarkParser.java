package com.github.pochi.birthmarks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.Birthmarks;
import com.github.pochi.birthmarks.entities.Element;
import com.github.pochi.birthmarks.entities.Elements;
import com.github.pochi.birthmarks.entities.Metadata;
import com.github.pochi.kunai.entries.Entry;
import com.github.pochi.kunai.source.DataSource;

public interface BirthmarkParser extends Task<ParserType> {
    @Override
    ParserType type();

    default List<Birthmark> parseForStream(DataSource source) {
        return source.stream()
                .filter(entry -> entry.endsWith(".csv"))
                .map(entry -> parseEntry(entry).stream())
                .reduce((first, second) -> Stream.concat(first, second))
                .map(stream -> stream.collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }
    
    default Birthmarks parse(DataSource source){
        List<Birthmark> stream = parseForStream(source);
        // do termination operation before calling ```type()``` 
        return new Birthmarks(stream.stream());
    }

    List<Birthmark> parseEntry(Entry entry);

    default Elements buildElements(Stream<String> stream){
        return new Elements(stream.map(Element::new));
    }

    Stream<Metadata> failedSources();    
}
