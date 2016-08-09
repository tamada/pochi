package com.github.kunai.source;

import java.io.IOException;
import java.util.stream.Stream;

import com.github.kunai.entries.Entry;

/**
 * This interface shows DataSource, which is the source of the some data.
 * 
 * @author Haruaki Tamada
 */
public interface DataSource extends AutoCloseable{
    Stream<Entry> stream();

    @Override
    void close() throws IOException;
}
