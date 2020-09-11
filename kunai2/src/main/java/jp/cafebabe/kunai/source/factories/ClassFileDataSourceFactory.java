package jp.cafebabe.kunai.source.factories;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import jp.cafebabe.kunai.entries.KunaiException;
import jp.cafebabe.kunai.util.PathHelper;
import jp.cafebabe.kunai.source.ClassFileDataSource;
import jp.cafebabe.kunai.source.DataSource;

class ClassFileDataSourceFactory implements DataSourceFactory{
    public ClassFileDataSourceFactory(){
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes){
        return PathHelper.endsWith(path, ".class")
                && attributes.isRegularFile();
    }

    @Override
    public DataSource build(Path path, FileSystem system) throws KunaiException {
        return new ClassFileDataSource(path);
    }
}
