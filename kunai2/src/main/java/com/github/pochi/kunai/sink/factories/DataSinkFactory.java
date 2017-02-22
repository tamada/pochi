package com.github.pochi.kunai.sink.factories;

import java.nio.file.Path;

import com.github.pochi.kunai.sink.DataSink;

public interface DataSinkFactory {
    DataSink create(Path path);

    boolean isTarget(Path path);
}
