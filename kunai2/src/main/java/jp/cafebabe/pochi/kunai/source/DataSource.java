package jp.cafebabe.pochi.kunai.source;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import jp.cafebabe.pochi.kunai.entries.Entry;

/**
 * This interface shows DataSource, which is the source of the some data.
 * 
 * @author Haruaki Tamada
 */
public interface DataSource extends AutoCloseable{
    Stream<Entry> stream();

    default DataSource filter(Predicate<Entry> predicate) {
        return new StreamDataSource(stream().filter(predicate));
    }

    default void forEach(Consumer<Entry> consumer){
        stream().forEach(consumer);
    }

    @Override
    void close() throws IOException;
}
