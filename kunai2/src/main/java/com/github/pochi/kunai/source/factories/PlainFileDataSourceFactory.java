package com.github.pochi.kunai.source.factories;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import com.github.pochi.kunai.entries.KunaiException;
import com.github.pochi.kunai.source.DataSource;
import com.github.pochi.kunai.source.PlainFileDataSource;

class PlainFileDataSourceFactory implements DataSourceFactory{
    public PlainFileDataSourceFactory(){
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes){
        return attributes.isRegularFile();
    }

    @Override
    public DataSource build(Path path, FileSystem system) throws KunaiException{
        return new PlainFileDataSource(path);
    }
}
