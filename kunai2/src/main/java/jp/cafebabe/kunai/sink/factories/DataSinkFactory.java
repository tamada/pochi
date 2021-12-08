package jp.cafebabe.kunai.sink.factories;

import java.nio.file.Path;

import jp.cafebabe.kunai.sink.DataSink;

public interface DataSinkFactory {
    DataSink create(Path path);

    boolean isTarget(Path path);

    static DataSinkFactory instance() {
        return new DefaultDataSinkFactory();
    }
}
