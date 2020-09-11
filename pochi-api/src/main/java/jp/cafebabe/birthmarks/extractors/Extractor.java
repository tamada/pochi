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

    default Stream<Either<Exception, Birthmark>> extractStream(DataSource source){
        return source.stream()
                .filter(entry -> entry.endsWith(".class"))
                .map(entry -> extractEach(entry));
    }

    default Birthmarks extract(DataSource source){
        return new Birthmarks(stripEither(extractStream(source)));
    }

    PochiClassVisitor visitor(ClassVisitor visitor);

    Either<Exception, Birthmark> extractEach(Entry entry);
}
