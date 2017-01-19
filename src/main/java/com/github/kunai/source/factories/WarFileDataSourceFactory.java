package com.github.kunai.source.factories;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import com.github.kunai.source.DataSource;
import com.github.kunai.source.WarFileDataSource;

class WarFileDataSourceFactory extends JarFileDataSourceFactory{
    public WarFileDataSourceFactory(){
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes){
        String name = path.toString();
        return name.endsWith(".war")
                && attributes.isRegularFile();
    }

    @Override
    DataSource buildDataSource(FileSystem system){
        return new WarFileDataSource(system);
    }
}
