package com.github.kunai.source.factories;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import com.github.kunai.entries.KunaiException;
import com.github.kunai.source.DataSource;
import com.github.kunai.source.DirectoryDataSource;

public class DirectoryDataSourceFactory implements DataSourceFactory{
    public DirectoryDataSourceFactory(){
    }

    @Override
    public boolean isTarget(Path path, BasicFileAttributes attributes){
        return attributes.isDirectory();
    }

    @Override
    public DataSource build(Path path) throws KunaiException{
        return new DirectoryDataSource(path);
    }
}
