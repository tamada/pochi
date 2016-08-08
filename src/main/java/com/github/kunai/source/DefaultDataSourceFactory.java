package com.github.kunai.source;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.github.kunai.entries.KunaiException;

public class DefaultDataSourceFactory extends DataSourceFactory{
    public DefaultDataSourceFactory(){
    }

    public DataSource build(Path path) throws KunaiException{
        String fileName = getFileName(path);
        if(fileName.endsWith(".jar")){
            try {
                ClassLoader loader = getClass().getClassLoader();
                FileSystem system = FileSystems.newFileSystem(path, loader);
                return new DefaultDataSource(system);
            } catch (IOException e) {
            }
        }

        throw new UnsupportedDataSourceException(path.toString());
    }

    public DataSource build(File file) throws KunaiException{
        return build(file.toPath());
    }

    public DataSource build(URI uri) throws KunaiException{
        FileSystem system = FileSystems.getFileSystem(uri);

        return new DefaultDataSource(system);
    }

    private String getFileName(Path path){
        return path.toString();
    }
}
