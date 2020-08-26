package jp.cafebabe.pochi.birthmarks.extractors;

import java.util.stream.Stream;

import io.vavr.control.Either;
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

    default Stream<Either<Exception, Birthmark>> extractStream(DataSource source){
        return source.stream()
                .filter(entry -> entry.endsWith(".class"))
                .map(entry -> extractEach(entry));
    }

    default Birthmarks extract(DataSource source){
        return new Birthmarks(extractStream(source)
                .filter(either -> either.isRight())
                .map(either -> either.get()));
    }

    PochiClassVisitor visitor(ClassVisitor visitor);

    Either<Exception, Birthmark> extractEach(Entry entry);
}
