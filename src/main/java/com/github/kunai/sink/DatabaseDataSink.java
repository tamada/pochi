package com.github.kunai.sink;

import java.io.IOException;

import com.github.kunai.entries.Entry;
import com.github.kunai.entries.KunaiException;

public class DatabaseDataSink extends AbstractDataSink {
    @Override
    public void close() throws IOException {
    }

    @Override
    public void consume(Entry entry, byte[] data) throws KunaiException {
        

    }
}
