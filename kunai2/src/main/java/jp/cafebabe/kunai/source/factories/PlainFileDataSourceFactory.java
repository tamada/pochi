package jp.cafebabe.kunai.source.factories;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import jp.cafebabe.kunai.entries.KunaiException;
import jp.cafebabe.kunai.source.DataSource;
import jp.cafebabe.kunai.source.PlainFileDataSource;

class PlainFileDataSourceFactory implements DataSourceFactory{
    public PlainFileDataSourceFactory(){
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes){
        return attributes.isRegularFile();
    }

    @Override
    public DataSource build(Path path, FileSystem system) throws KunaiException {
        return new PlainFileDataSource(path);
    }
}
