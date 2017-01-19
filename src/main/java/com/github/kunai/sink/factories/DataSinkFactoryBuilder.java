package com.github.kunai.sink.factories;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.github.kunai.sink.ClassFileDataSink;
import com.github.kunai.sink.DataSink;
import com.github.kunai.sink.DirectoryDataSink;
import com.github.kunai.sink.JarFileDataSink;
import com.github.kunai.sink.WarFileDataSink;

public class DataSinkFactoryBuilder {
    private List<DataSinkFactory> factories = new ArrayList<>();

    public DataSinkFactoryBuilder(){
        factories.add(new DataSinkFactory(path -> path.endsWith(".jar"),   path -> new JarFileDataSink(path)));
        factories.add(new DataSinkFactory(path -> path.endsWith(".war"),   path -> new WarFileDataSink(path)));
        factories.add(new DataSinkFactory(path -> path.endsWith(".class"), path -> new ClassFileDataSink(path)));
        factories.add(new DataSinkFactory(path -> true,                    path -> new DirectoryDataSink(path)));
    }

    public DataSinkFactory factory(Path path){
        return factories.stream()
                .filter(factory -> factory.test(path))
                .findFirst().get();
    }

    public DataSink create(Path path){
        return factory(path).create(path);
    }
}
