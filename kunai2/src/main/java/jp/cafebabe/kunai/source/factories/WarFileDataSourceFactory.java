package jp.cafebabe.kunai.source.factories;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import jp.cafebabe.kunai.util.PathHelper;
import jp.cafebabe.kunai.source.DataSource;
import jp.cafebabe.kunai.source.WarFileDataSource;

class WarFileDataSourceFactory extends JarFileDataSourceFactory{
    public WarFileDataSourceFactory(){
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes){
        return PathHelper.endsWith(path, ".war")
                && attributes.isRegularFile();
    }

    @Override
    DataSource buildDataSource(Path path, FileSystem system){
        return new WarFileDataSource(path, system);
    }
}
