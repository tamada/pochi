package com.github.ebis.birthmarks;

import java.util.Optional;
import java.util.stream.Stream;

import org.objectweb.asm.ClassVisitor;

import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.birthmarks.entities.BirthmarkType;
import com.github.ebis.birthmarks.entities.Birthmarks;
import com.github.ebis.birthmarks.entities.ExtractionResults;
import com.github.ebis.config.Configuration;
import com.github.kunai.entries.Entry;
import com.github.kunai.source.DataSource;

public interface BirthmarkExtractor {
    BirthmarkType type();

    default ExtractionResults extract(DataSource source, Configuration context){
        Stream<Birthmark> stream = source.stream()
                .filter(entry -> entry.isClass())
                .map(entry -> extractEach(entry, context))
                .filter(item -> item.isPresent())
                .map(item -> item.get());
        return new ExtractionResults(type(), new Birthmarks(stream));
    }

    EbisClassVisitor visitor(ClassVisitor parent, Configuration context);

    Optional<Birthmark> extractEach(Entry entry, Configuration context);
}
