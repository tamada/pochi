package jp.cafebabe.birthmarks;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.Elements;
import jp.cafebabe.birthmarks.entities.Metadata;
import jp.cafebabe.kunai.entries.Entry;
import jp.cafebabe.kunai.source.DataSource;

public interface BirthmarkParser extends Task<ParserType> {
    @Override
    ParserType type();

    default List<Birthmark> parseForStream(DataSource source) {
        return source.stream()
                .filter(entry -> entry.endsWith(".csv"))
                .flatMap(entry -> parseEntry(entry).stream())
                .collect(Collectors.toList());
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
