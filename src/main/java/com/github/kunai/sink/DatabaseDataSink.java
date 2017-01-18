package com.github.kunai.sink;

import java.io.IOException;
import java.io.InputStream;

import com.github.kunai.entries.Entry;
import com.github.kunai.entries.KunaiException;

public class DatabaseDataSink extends AbstractDataSink {
    @Override
    public void close() throws IOException {
    }

    @Override
    public void consume(InputStream in, Entry entry) throws KunaiException {
    }
}
