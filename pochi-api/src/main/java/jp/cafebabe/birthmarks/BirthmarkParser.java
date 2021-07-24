package jp.cafebabe.birthmarks;

import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.entities.Elements;
import jp.cafebabe.birthmarks.entities.Metadata;
import jp.cafebabe.kunai.entries.Entry;
import jp.cafebabe.kunai.source.DataSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface BirthmarkParser<String> extends Task<ParserType> {
    @Override
    ParserType type();

    default List<Birthmark<String>> parseForStream(DataSource source) {
        return source.stream()
                .filter(entry -> entry.endsWith(".csv"))
                .flatMap(entry -> parseEntry(entry).stream())
                .collect(Collectors.toList());
    }
    
    default Birthmarks<String> parse(DataSource source){
        List<Birthmark<String>> stream = parseForStream(source);
        // do termination operation before calling ```type()``` 
        return new Birthmarks<>(stream.stream());
    }

    List<Birthmark<String>> parseEntry(Entry entry);

    default Elements<String> buildElements(Stream<String> stream){
        return new Elements<String>(stream);
    }

    Stream<Metadata> failedSources();    
}
