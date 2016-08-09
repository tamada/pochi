package com.github.kunai.sink;

import java.io.IOException;

import com.github.kunai.entries.Entry;
import com.github.kunai.entries.KunaiException;

public interface DataSink extends AutoCloseable{
    void putEntry(Entry entry) throws KunaiException;

    @Override
    void close() throws IOException;
}
