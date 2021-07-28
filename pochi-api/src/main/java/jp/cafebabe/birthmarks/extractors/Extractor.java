package jp.cafebabe.birthmarks.extractors;

import java.util.stream.Stream;

import io.vavr.control.Either;
import org.objectweb.asm.ClassVisitor;

import jp.cafebabe.birthmarks.Task;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.kunai.entries.Entry;
import jp.cafebabe.kunai.source.DataSource;

public interface Extractor extends Task<BirthmarkType>{
    @Override
    BirthmarkType type();

    default <T> Stream<Either<Exception, Birthmark<T>>> extractStream(DataSource source){
        return source.stream()
                .filter(entry -> entry.endsWith(".class"))
                .map(entry -> extractEach(entry));
    }

    default <T> Birthmarks<T> extract(DataSource source){
        return new Birthmarks<T>(stripEither(extractStream(source)));
    }

    <T> PochiClassVisitor<T> visitor(ClassVisitor visitor);

    <T> Either<Exception, Birthmark<T>> extractEach(Entry entry);
}
