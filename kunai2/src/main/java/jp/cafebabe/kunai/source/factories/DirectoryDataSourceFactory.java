package jp.cafebabe.kunai.source.factories;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import jp.cafebabe.kunai.entries.KunaiException;
import jp.cafebabe.kunai.source.DirectoryDataSource;
import jp.cafebabe.kunai.source.DataSource;

class DirectoryDataSourceFactory implements DataSourceFactory{
    public DirectoryDataSourceFactory(){
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes){
        return attributes.isDirectory();
    }

    @Override
    public DataSource build(Path path, FileSystem system) throws KunaiException {
        if(!isTarget(path, system))
            throw new UnsupportedDataSourceException(path + ": not supported.");
        return new DirectoryDataSource(path);
    }
}
