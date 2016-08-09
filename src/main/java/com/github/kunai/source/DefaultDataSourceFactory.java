package com.github.kunai.source;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;

import com.github.kunai.entries.KunaiException;

public class DefaultDataSourceFactory extends DataSourceFactory{
    public DefaultDataSourceFactory(){
    }

    public DataSource build(Path path) throws KunaiException{
        try{
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);

            return buildSource(path, attr);
        } catch(IOException e){
            e.printStackTrace();
        }
        throw new UnsupportedDataSourceException(path.toString());
    }

    private DataSource buildSource(Path path, BasicFileAttributes attr) throws IOException{
        String fileName = getFileName(path);

        if(fileName.endsWith(".jar")){
            ClassLoader loader = getClass().getClassLoader();
            FileSystem system = FileSystems.newFileSystem(path, loader);
            return new JarFileDataSource(system);
        }
        else if(fileName.endsWith(".war")){
            ClassLoader loader = getClass().getClassLoader();
            FileSystem system = FileSystems.newFileSystem(path, loader);
            return new WarFileDataSource(system);
        }
        else if(attr.isDirectory()){
            return new DirectoryDataSource(path);
        }
            
        return null;
    }

    public DataSource build(File file) throws KunaiException{
        return build(file.toPath());
    }

    public DataSource build(URI uri) throws KunaiException{
        FileSystem system = FileSystems.getFileSystem(uri);

        return new JarFileDataSource(system);
    }

    private String getFileName(Path path){
        return path.toString();
    }
}
