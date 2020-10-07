package jp.cafebabe.pochi;

import java.util.List;
import java.util.stream.Stream;

import jp.cafebabe.birthmarks.AbstractTask;
import jp.cafebabe.birthmarks.BirthmarkParser;
import jp.cafebabe.birthmarks.ParserType;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Metadata;
import jp.cafebabe.kunai.entries.Entry;

public abstract class AbstractParser extends AbstractTask<ParserType> implements BirthmarkParser {
    public AbstractParser(ParserType type, Configuration config) {
        super(type, config);
    }

    public abstract List<Birthmark> parseEntry(Entry entry);

    public abstract Stream<Metadata> failedSources();    
}
