package jp.cafebabe.pochi.runner.birthmarks;

import java.util.List;
import java.util.stream.Stream;

import jp.cafebabe.pochi.birthmarks.AbstractTask;
import jp.cafebabe.pochi.birthmarks.BirthmarkParser;
import jp.cafebabe.pochi.birthmarks.ParserType;
import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.birthmarks.entities.Metadata;
import jp.cafebabe.pochi.kunai.entries.Entry;

public abstract class AbstractParser extends AbstractTask<ParserType> implements BirthmarkParser{
    public AbstractParser(ParserType type, Configuration config) {
        super(type, config);
    }

    public abstract List<Birthmark> parseEntry(Entry entry);

    public abstract Stream<Metadata> failedSources();    
}
