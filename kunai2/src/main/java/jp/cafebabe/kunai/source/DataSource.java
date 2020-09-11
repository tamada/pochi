package jp.cafebabe.kunai.source;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import jp.cafebabe.kunai.entries.Entry;

/**
 * This interface shows DataSource, which is the source of the some data.
 * 
 * @author Haruaki Tamada
 */
public interface DataSource extends AutoCloseable{
    Path base();

    Stream<Entry> stream();

    default DataSource filter(Predicate<Entry> predicate) {
        return new FilteredDataSource(this, predicate);
    }

    default void forEach(Consumer<Entry> consumer){
        stream().forEach(consumer);
    }

    @Override
    void close() throws IOException;
}
