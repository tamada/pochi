package jp.cafebabe.pochi.kunai.source.factories;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import jp.cafebabe.pochi.kunai.entries.KunaiException;
import jp.cafebabe.pochi.kunai.source.ClassFileDataSource;
import jp.cafebabe.pochi.kunai.source.DataSource;
import jp.cafebabe.pochi.kunai.util.PathHelper;

class ClassFileDataSourceFactory implements DataSourceFactory{
    public ClassFileDataSourceFactory(){
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes){
        return PathHelper.endsWith(path, ".class")
                && attributes.isRegularFile();
    }

    @Override
    public DataSource build(Path path, FileSystem system) throws KunaiException{
        return new ClassFileDataSource(path);
    }
}
