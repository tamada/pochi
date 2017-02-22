package com.github.pochi.kunai.sink;

import java.io.IOException;

public abstract class AbstractDataSink implements DataSink{
    public AbstractDataSink(){
    }

    @Override
    public void close() throws IOException {
    }
}
