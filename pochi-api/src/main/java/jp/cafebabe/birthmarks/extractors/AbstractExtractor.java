package jp.cafebabe.birthmarks.extractors;

import java.io.IOException;
import java.io.InputStream;

import io.vavr.control.Either;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import jp.cafebabe.birthmarks.AbstractTask;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.kunai.entries.Entry;

public abstract class AbstractExtractor extends AbstractTask<BirthmarkType> implements Extractor {
    public AbstractExtractor(BirthmarkType type, Configuration config){
        super(type, config);
    }

    @Override
    public final <T> Either<Exception, Birthmark<T>> extractEach(Entry entry) {
        try {
            return Either.right(extractImpl(entry));
        } catch (Exception e) {
            return Either.left(e);
        }
    }

    private <T> Birthmark<T> extractImpl(Entry entry) throws IOException {
        try (InputStream in = entry.openStream()) {
            PochiClassVisitor<T> visitor = visitor(new ClassWriter(0));
            return accept(in, entry, visitor);
        }
    }

    private <T> Birthmark<T> accept(InputStream in, Entry entry, PochiClassVisitor<T> visitor) throws IOException {
        ClassReader reader = new ClassReader(in);
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        return visitor.build(entry);
    }
}
