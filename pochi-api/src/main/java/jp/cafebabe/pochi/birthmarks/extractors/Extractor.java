package jp.cafebabe.pochi.birthmarks.extractors;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import jp.cafebabe.pochi.birthmarks.pairs.Pair;
import jp.cafebabe.pochi.nasubi.Either;
import org.objectweb.asm.ClassVisitor;

import jp.cafebabe.pochi.birthmarks.Task;
import jp.cafebabe.pochi.birthmarks.entities.Birthmark;
import jp.cafebabe.pochi.birthmarks.entities.BirthmarkType;
import jp.cafebabe.pochi.birthmarks.entities.Birthmarks;
import jp.cafebabe.pochi.kunai.entries.Entry;
import jp.cafebabe.pochi.kunai.source.DataSource;

public interface Extractor extends Task<BirthmarkType>{
    @Override
    BirthmarkType type();

    default Stream<Either<Exception, Birthmark>> extractForStream(DataSource source){
        return source.stream()
                .filter(entry -> entry.endsWith(".class"))
                .map(entry -> extractEach(entry));
    }

    default Birthmarks extract(DataSource source){
        return new Birthmarks(extractForStream(source)
                .flatMap(either -> either.rightStream()));
    }

    PochiClassVisitor visitor(ClassVisitor visitor);

    Either<Exception, Birthmark> extractEach(Entry entry);
}
