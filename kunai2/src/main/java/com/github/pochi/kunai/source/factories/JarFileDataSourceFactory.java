package com.github.pochi.kunai.source.factories;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import com.github.pochi.kunai.entries.KunaiException;
import com.github.pochi.kunai.source.DataSource;
import com.github.pochi.kunai.source.JarFileDataSource;
import com.github.pochi.kunai.util.PathHelper;

class JarFileDataSourceFactory implements DataSourceFactory{
    public JarFileDataSourceFactory(){
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes){
        return PathHelper.endsWith(path, ".jar")
                && attributes.isRegularFile();
    }

    @Override
    public DataSource build(Path path, FileSystem system) throws KunaiException{
        try{ return buildImpl(path, system); }
        catch(IOException e){
            throw new UnsupportedDataSourceException(e.getMessage()); 
        }
    }

    private DataSource buildImpl(Path path, FileSystem system) throws KunaiException, IOException{
        if(!isTarget(path, system))
            throw new UnsupportedDataSourceException(path + ": not supported");
        return buildDataSource(path, system);
    }

    private DataSource buildDataSource(Path path, FileSystem system) throws IOException{
        ClassLoader loader = getClass().getClassLoader();
        FileSystem jarSystem = FileSystems.newFileSystem(path, loader);
        return buildDataSource(jarSystem);
    }

    DataSource buildDataSource(FileSystem system){
        return new JarFileDataSource(system);
    }
}
