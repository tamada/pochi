package com.github.kunai.source.factories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
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
    public DataSource build(Path path) throws KunaiException{
        try{
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
            Optional<DataSourceFactory> source = factories.stream()
                    .filter(factory -> factory.isTarget(path, attr))
                    .findFirst();

            return build(source, path);
        } catch (IOException e) {
            throw new KunaiException(e.getLocalizedMessage());
        }
    }

    private DataSource build(Optional<DataSourceFactory> source, Path path) throws KunaiException{
        if(source.isPresent()){
            DataSourceFactory factory = source.get();
            return factory.build(path);
        }
        throw new UnsupportedDataSourceException(path.toString());
    }

    @Override
    public boolean isTarget(Path path, BasicFileAttributes attributes) {
        return true;
    }

    @Override
    public boolean isTarget(Path path) {
        return true;
    }
}
