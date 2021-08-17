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

/**
 * Extracting birthmarks from the given DataSource.
 */
public interface Extractor extends Task<BirthmarkType>{
    /**
     * returns the type of the birthmark extracting by this extractor.
     * @return
     */
    @Override
    BirthmarkType type();

    /**
     * Extract Birthmarks from the given DataSource as Stream
     * @param source extraction source
     * @param <T> element type of the extracted birthmark
     * @return extracted birthmarks
     */
    default <T> Stream<Either<Exception, Birthmark<T>>> extractStream(DataSource source){
        return source.stream()
                .filter(entry -> entry.endsWith(".class"))
                .map(entry -> extractEach(entry));
    }

    /**
     * Extract birthmarks from the given DataSource as Birthmarks which is the container class for the {@link Birthmark <code>Birthmark</code>}s.
     * @param source extraction source
     * @param <T> element type of the extracted birthmarks
     * @return extracted birthmarks
     */
    default <T> Birthmarks<T> extract(DataSource source){
        return new Birthmarks<T>(stripEither(extractStream(source)));
    }

    /**
     *
     * @param visitor
     * @param <T>
     * @return
     */
    <T> PochiClassVisitor<T> visitor(ClassVisitor visitor);

    /**
     *
     * @param entry
     * @param <T>
     * @return
     */
    <T> Either<Exception, Birthmark<T>> extractEach(Entry entry);
}
