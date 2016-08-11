package com.github.kunai.source.factories;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.kunai.entries.KunaiException;
import com.github.kunai.source.DataSource;

public class DefaultDataSourceFactory implements DataSourceFactory{
    private List<DataSourceFactory> factories = new ArrayList<>();

    public DefaultDataSourceFactory(){
        factories.add(new JarFileDataSourceFactory());
        factories.add(new WarFileDataSourceFactory());
        factories.add(new ClassFileDataSourceFactory());
        factories.add(new DirectoryDataSourceFactory());
    }

    @Override
    public DataSource build(Path path, FileSystem system) throws KunaiException{
        try{
            FileSystemProvider provider = system.provider();
            BasicFileAttributes attr = provider.readAttributes(path, BasicFileAttributes.class);
            Optional<DataSourceFactory> source = factories.stream()
                    .filter(factory -> factory.isTarget(path, system, attr))
                    .findFirst();

            return build(source, path);
        } catch (IOException e) {
            throw new KunaiException(e.getLocalizedMessage());
        }
    }

    private DataSource build(Optional<DataSourceFactory> source, Path path) throws KunaiException{
        return source
                .orElseThrow(() -> new UnsupportedDataSourceException(path.toString()))
                .build(path);
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes) {
        return true;
    }

    @Override
    public boolean isTarget(Path path) {
        return true;
    }
}
