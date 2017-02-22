package com.github.pochi.kunai.source;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.github.pochi.kunai.entries.Entry;

/**
 * This interface shows DataSource, which is the source of the some data.
 * 
 * @author Haruaki Tamada
 */
public interface DataSource extends AutoCloseable{
    Stream<Entry> stream();

    default void forEach(Consumer<Entry> consumer){
        stream().forEach(consumer);
    }

    @Override
    void close() throws IOException;
}
