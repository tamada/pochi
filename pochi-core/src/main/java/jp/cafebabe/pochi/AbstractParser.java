package jp.cafebabe.pochi;

import jp.cafebabe.birthmarks.AbstractTask;
import jp.cafebabe.birthmarks.BirthmarkParser;
import jp.cafebabe.birthmarks.ParserType;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Metadata;
import jp.cafebabe.kunai.entries.Entry;

import java.util.List;
import java.util.stream.Stream;

public abstract class AbstractParser extends AbstractTask<ParserType> implements BirthmarkParser {
    public AbstractParser(ParserType type, Configuration config) {
        super(type, config);
    }

    public abstract List<Birthmark<String>> parseEntry(Entry entry);

    public abstract Stream<Metadata> failedSources();    
}
