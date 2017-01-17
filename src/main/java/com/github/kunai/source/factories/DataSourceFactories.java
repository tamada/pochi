package com.github.kunai.source.factories;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataSourceFactories{
    private List<DataSourceFactory> factories = new ArrayList<>();

    public DataSourceFactories(){
        factories.add(new JarFileDataSourceFactory());
        factories.add(new WarFileDataSourceFactory());
        factories.add(new ClassFileDataSourceFactory());
        factories.add(new DirectoryDataSourceFactory());
    }

    public Optional<DataSourceFactory> find(Path path, FileSystem system) throws IOException{
        return find(path, system, system.provider());
    }

    private Optional<DataSourceFactory> find(Path path, FileSystem system,
            FileSystemProvider provider) throws IOException{
        Class<BasicFileAttributes> clazz = BasicFileAttributes.class;
        return find(path, system, provider.readAttributes(path, clazz));
    }

    private Optional<DataSourceFactory> find(Path path, FileSystem system,
            BasicFileAttributes attr) throws IOException{
        return factories.stream()
                .filter(factory -> factory.isTarget(path, system, attr))
                .findFirst();
    }
}
