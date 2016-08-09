package com.github.kunai.source.factories;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import com.github.kunai.entries.KunaiException;
import com.github.kunai.source.DataSource;
import com.github.kunai.source.JarFileDataSource;

public class JarFileDataSourceFactory implements DataSourceFactory{
    public JarFileDataSourceFactory(){
    }

    @Override
    public boolean isTarget(Path path, BasicFileAttributes attributes){
        String name = path.toString();
        return name.endsWith(".jar") && attributes.isRegularFile();
    }

    @Override
    public DataSource build(Path path) throws KunaiException{
        try{
            ClassLoader loader = getClass().getClassLoader();
            FileSystem system = FileSystems.newFileSystem(path, loader);
            return buildDataSource(system);
        } catch(IOException e){
            throw new UnsupportedDataSourceException(e.getMessage());
        }
    }

    DataSource buildDataSource(FileSystem system){
        return new JarFileDataSource(system);
    }
}
