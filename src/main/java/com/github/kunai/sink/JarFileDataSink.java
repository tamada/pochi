package com.github.kunai.sink;

import java.io.IOException;
import java.nio.file.FileSystem;

import com.github.kunai.entries.Entry;
import com.github.kunai.entries.KunaiException;

public class JarFileDataSink extends AbstractDataSink {
    private FileSystem system;

    public JarFileDataSink(FileSystem system){
        this.system = system;
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public void consume(Entry entry, byte[] data) throws KunaiException {
        entry.path();
    }
}
