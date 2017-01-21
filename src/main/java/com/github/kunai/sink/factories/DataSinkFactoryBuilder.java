package com.github.kunai.sink.factories;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.github.kunai.sink.ClassFileDataSink;
import com.github.kunai.sink.DataSink;
import com.github.kunai.sink.DirectoryDataSink;
import com.github.kunai.sink.JarFileDataSink;
import com.github.kunai.sink.WarFileDataSink;
import com.github.kunai.util.PathHelper;

public class DataSinkFactoryBuilder {
    private List<DataSinkFactory> factories = new ArrayList<>();

    public DataSinkFactoryBuilder(){
        register(new DataSinkFactory(path -> PathHelper.endsWith(path, ".jar"),   path -> new JarFileDataSink(path)));
        register(new DataSinkFactory(path -> PathHelper.endsWith(path, ".war"),   path -> new WarFileDataSink(path)));
        register(new DataSinkFactory(path -> PathHelper.endsWith(path, ".class"), path -> new ClassFileDataSink(path)));
        register(new DataSinkFactory(path -> true,                                path -> new DirectoryDataSink(path)));
    }

    public DataSinkFactory factory(Path path){
        return factories.stream()
                .filter(factory -> factory.test(path))
                .findFirst().get();
    }

    public DataSink create(Path path){
        return factory(path).create(path);
    }

    private void register(DataSinkFactory factory){
        factories.add(factory);
    }
}
