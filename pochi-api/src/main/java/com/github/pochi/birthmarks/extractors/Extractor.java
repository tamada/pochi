package com.github.pochi.birthmarks.extractors;

import java.util.Optional;
import java.util.stream.Stream;

import org.objectweb.asm.ClassVisitor;

import com.github.pochi.birthmarks.Task;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.BirthmarkType;
import com.github.pochi.birthmarks.entities.Birthmarks;
import com.github.pochi.birthmarks.entities.Metadata;
import com.github.pochi.kunai.entries.Entry;
import com.github.pochi.kunai.source.DataSource;

public interface Extractor extends Task<BirthmarkType>{
    @Override
    BirthmarkType type();

    default Stream<Birthmark> extractForStream(DataSource source){
        Stream<Optional<Birthmark>> stream = source.stream()
                .filter(entry -> entry.endsWith(".class"))
                .map(entry -> extractEach(entry));
        return filter(stream);
    }

    default Birthmarks extract(DataSource source){
        return new Birthmarks(extractForStream(source));
    }

    PochiClassVisitor visitor(ClassVisitor visitor);

    Optional<Birthmark> extractEach(Entry entry);

    Stream<Metadata> failedSources();
}
