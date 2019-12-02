package jp.cafebabe.pochi.kunai.sink.factories;

import java.nio.file.Path;

import jp.cafebabe.pochi.kunai.sink.DataSink;

public interface DataSinkFactory {
    DataSink create(Path path);

    boolean isTarget(Path path);
}
