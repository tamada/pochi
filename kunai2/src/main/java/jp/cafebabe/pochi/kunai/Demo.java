package jp.cafebabe.pochi.kunai;

import java.nio.file.Paths;

import jp.cafebabe.pochi.kunai.sink.DataSink;
import jp.cafebabe.pochi.kunai.sink.factories.DefaultDataSinkFactory;
import jp.cafebabe.pochi.kunai.source.DataSource;
import jp.cafebabe.pochi.kunai.source.factories.DefaultDataSourceFactory;

public class Demo {
    public Demo(String[] args) throws Exception{
        copy(args[0], args[1]);
    }

    private void copy(String from, String to) throws Exception{
        try(DataSource source = new DefaultDataSourceFactory().build(Paths.get(from));
                DataSink sink = new DefaultDataSinkFactory().create(Paths.get(to))){
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
