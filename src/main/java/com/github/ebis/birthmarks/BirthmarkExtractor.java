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

    default Results<Birthmarks> extract(DataSource source, Configuration context){
        Stream<Birthmark> stream = source.stream()
                .filter(entry -> entry.isClass())
                .map(entry -> extractEach(entry, context))
                .filter(item -> item.isPresent())
                .map(item -> item.get());
        return new Results<>(type(), new Birthmarks(stream));
    }

    EbisClassVisitor visitor(ClassVisitor parent, Configuration context);

    Optional<Birthmark> extractEach(Entry entry, Configuration context);

    Stream<Metadata> failedSources();
}
