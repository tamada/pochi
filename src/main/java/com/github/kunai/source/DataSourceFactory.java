package com.github.kunai.source;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;

import com.github.kunai.entries.KunaiException;

public abstract class DataSourceFactory {
    public DataSourceFactory(){
    }

    public abstract DataSource build(Path path) throws KunaiException;

    public DataSource build(File file) throws KunaiException{
        return build(file.toPath());
    }

    public abstract DataSource build(URI uri) throws KunaiException;
}
