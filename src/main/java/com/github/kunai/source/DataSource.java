package com.github.kunai.source;

import java.io.IOException;
import java.util.stream.Stream;

import com.github.kunai.entries.Entry;

public interface DataSource {
    Stream<Entry> stream();

    void close() throws IOException;
}
