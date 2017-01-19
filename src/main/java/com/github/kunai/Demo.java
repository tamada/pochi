package com.github.kunai;

import java.nio.file.Paths;

import com.github.kunai.sink.DataSink;
import com.github.kunai.sink.factories.DataSinkFactoryBuilder;
import com.github.kunai.source.DataSource;
import com.github.kunai.source.factories.DefaultDataSourceFactory;

public class Demo {
    public Demo(String[] args) throws Exception{
        copy(args[0], args[1]);
    }

    private void copy(String from, String to) throws Exception{
        try(DataSource source = new DefaultDataSourceFactory().build(Paths.get(from));
                DataSink sink = new DataSinkFactoryBuilder().create(Paths.get(to))){
            copy(source, sink);
        }
    }

    private void copy(DataSource source, DataSink sink) throws Exception{
        sink.consume(source);
    }

    public static final void main(String[] args) throws Exception{
        new Demo(args);
    }
}
