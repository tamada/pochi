package com.github.kunai.source.factories;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import com.github.kunai.entries.KunaiException;
import com.github.kunai.source.DataSource;

public interface DataSourceFactory {
    boolean isTarget(Path path, BasicFileAttributes attributes);

    default boolean isTarget(Path path){
        try{
            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            return isTarget(path, attributes);
        } catch(IOException e){
        }
        return false;
    }

    DataSource build(Path path) throws KunaiException;

    default DataSource build(File file) throws KunaiException{
        return build(file.toPath());
    }
}
