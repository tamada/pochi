package jp.cafebabe.pochi.birthmarks.extractors;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.function.BiConsumer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import jp.cafebabe.pochi.birthmarks.AbstractTask;
import jp.cafebabe.pochi.birthmarks.config.Configuration;
import jp.cafebabe.pochi.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.kunai.entries.Entry;

public abstract class AbstractExtractor extends AbstractTask<BirthmarkType> implements Extractor {
    public AbstractExtractor(BirthmarkType type, Configuration config){
        super(type, config);
    }

    @Override
    public final Optional<Birthmark> extractEach(Entry entry, BiConsumer<Entry, Exception> callbackOnError) {
        try {
            return Optional.of(extractImpl(entry));
        } catch (Exception e) {
            callbackOnError.accept(entry, e);
        }
        return Optional.empty();
    }

    private Birthmark extractImpl(Entry entry) throws IOException {
        try (InputStream in = entry.openStream()) {
            PochiClassVisitor visitor = visitor(new ClassWriter(0));
            return accept(in, entry, visitor);
        }
    }

    private Birthmark accept(InputStream in, Entry entry, PochiClassVisitor visitor) throws IOException {
        ClassReader reader = new ClassReader(in);
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        return visitor.build(entry);
    }
}
