package com.github.pochi.birthmarks.extractors;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import org.objectweb.asm.ClassVisitor;

import com.github.pochi.birthmarks.Task;
import com.github.pochi.birthmarks.entities.Birthmark;
import com.github.pochi.birthmarks.entities.BirthmarkType;
import com.github.pochi.birthmarks.entities.Birthmarks;
import com.github.pochi.kunai.entries.Entry;
import com.github.pochi.kunai.source.DataSource;

public interface Extractor extends Task<BirthmarkType>{
    @Override
    BirthmarkType type();

    default Stream<Birthmark> extractForStream(DataSource source){
        return extractForStream(source, (entry, exception) -> {});
    }

    default Stream<Birthmark> extractForStream(DataSource source, BiConsumer<Entry, Exception> callbackOnError){
        return filter(source.stream()
                .filter(entry -> entry.endsWith(".class"))
                .map(entry -> extractEach(entry, callbackOnError)));
    }

    default Birthmarks extract(DataSource source){
        return extract(source, (entry, exception) -> {});
    }

    default Birthmarks extract(DataSource source, BiConsumer<Entry, Exception> action){
        return new Birthmarks(extractForStream(source, action));
    }

    PochiClassVisitor visitor(ClassVisitor visitor);

    Optional<Birthmark> extractEach(Entry entry, BiConsumer<Entry, Exception> action);
}
