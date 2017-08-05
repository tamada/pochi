package com.github.pochi.birthmarks.extractors;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.stream.Stream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import com.github.pochi.birthmarks.AbstractTask;
import com.github.pochi.birthmarks.config.Configuration;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.BirthmarkType;
import com.github.pochi.birthmarks.entities.FailedSources;
import com.github.pochi.birthmarks.entities.Metadata;
import com.github.pochi.kunai.entries.Entry;

public abstract class AbstractExtractor extends AbstractTask<BirthmarkType> implements Extractor {
    private FailedSources failedSources = new FailedSources();
    
    public AbstractExtractor(BirthmarkType type, Configuration config){
        super(type, config);
    }

    @Override
    public final Optional<Birthmark> extractEach(Entry entry) {
        try {
            return Optional.of(extractImpl(entry));
        } catch (IOException e) {
            
        }
        failedSources.add(Metadata.build(entry));
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

    @Override
    public final Stream<Metadata> failedSources() {
        return failedSources.stream();
    }
}
