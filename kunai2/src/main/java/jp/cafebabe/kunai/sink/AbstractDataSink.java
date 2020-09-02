package jp.cafebabe.kunai.sink;

import java.io.IOException;

import jp.cafebabe.kunai.entries.Entry;

public abstract class AbstractDataSink implements DataSink {
    @Override
    public void close() throws IOException {
    }

    String toJVMClassName(Entry entry){
        if(!entry.isClass())
            throw new IllegalArgumentException();
        return convertToJVMClassName(entry);
    }

    private String convertToJVMClassName(Entry entry){
        return entry.className()
            .toString()
            .replace('.', '/');
    }
}
