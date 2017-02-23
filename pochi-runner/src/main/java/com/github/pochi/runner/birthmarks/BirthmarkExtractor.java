package com.github.pochi.runner.birthmarks;

import java.util.Optional;
import java.util.stream.Stream;

import org.objectweb.asm.ClassVisitor;

import com.github.pochi.kunai.entries.Entry;
import com.github.pochi.kunai.source.DataSource;
import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.BirthmarkType;
import com.github.pochi.runner.birthmarks.entities.Birthmarks;
import com.github.pochi.runner.birthmarks.entities.Metadata;
import com.github.pochi.runner.config.Configuration;

public interface BirthmarkExtractor extends Service<BirthmarkType>{
    BirthmarkType type();

    default Stream<Birthmark> extractForStream(DataSource source, Configuration context){
        Stream<Optional<Birthmark>> stream = source.stream()
                .filter(entry -> entry.endsWith(".class"))
                .map(entry -> extractEach(entry, context));
        return filter(stream);
    }

    default Birthmarks extract(DataSource source, Configuration context){
        return new Birthmarks(extractForStream(source, context));
    }

    PochiClassVisitor visitor(ClassVisitor parent, Configuration context);

    Optional<Birthmark> extractEach(Entry entry, Configuration context);

    Stream<Metadata> failedSources();
}
