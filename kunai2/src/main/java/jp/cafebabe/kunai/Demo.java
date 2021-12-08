package jp.cafebabe.kunai;

import java.nio.file.Paths;

import jp.cafebabe.kunai.sink.DataSink;
import jp.cafebabe.kunai.sink.factories.DataSinkFactory;
import jp.cafebabe.kunai.source.DataSource;
import jp.cafebabe.kunai.source.factories.DataSourceFactory;

public class Demo {
    public Demo(String[] args) throws Exception{
        copy(args[0], args[1]);
    }

    private void copy(String from, String to) throws Exception{
        try(DataSource source = DataSourceFactory.instance().build(Paths.get(from));
                DataSink sink = DataSinkFactory.instance().create(Paths.get(to))) {
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
