package com.github.ebis.birthmarks;

import java.util.Optional;
import java.util.stream.Stream;

import org.objectweb.asm.ClassVisitor;

import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.birthmarks.entities.BirthmarkType;
import com.github.ebis.birthmarks.entities.Birthmarks;
import com.github.ebis.birthmarks.entities.Metadata;
import com.github.ebis.birthmarks.entities.Results;
import com.github.ebis.config.Configuration;
import com.github.kunai.entries.Entry;
import com.github.kunai.source.DataSource;

public interface BirthmarkExtractor extends Service<BirthmarkType>{
    BirthmarkType type();

    default Stream<Birthmark> extractForStream(DataSource source, Configuration context){
        Stream<Optional<Birthmark>> stream = source.stream()
                .filter(entry -> entry.endsWith(".class"))
                .map(entry -> extractEach(entry, context));
        return filter(stream);
    }

    default Results<Birthmarks> extract(DataSource source, Configuration context){
        return new Results<>(type(), new Birthmarks(extractForStream(source, context)));
    }

    EbisClassVisitor visitor(ClassVisitor parent, Configuration context);

    Optional<Birthmark> extractEach(Entry entry, Configuration context);

    Stream<Metadata> failedSources();
}
