package com.github.pochi.birthmarks.extractors;

import java.util.Optional;
import java.util.function.Consumer;
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
        return extractForStream(source, entry -> {});
    }
    default Stream<Birthmark> extractForStream(DataSource source, Consumer<Entry> action){
        Stream<Optional<Birthmark>> stream = source.stream()
                .filter(entry -> entry.endsWith(".class"))
                .map(entry -> extractEach(entry, action));
        return filter(stream);
    }

    default Birthmarks extract(DataSource source){
        return extract(source, entry -> {});
    }

    default Birthmarks extract(DataSource source, Consumer<Entry> action){
        return new Birthmarks(extractForStream(source, action));
    }

    PochiClassVisitor visitor(ClassVisitor visitor);

    Optional<Birthmark> extractEach(Entry entry, Consumer<Entry> action);

    Stream<Metadata> failedSources();
}
