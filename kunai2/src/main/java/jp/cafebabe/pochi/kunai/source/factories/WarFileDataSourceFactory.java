package jp.cafebabe.pochi.kunai.source.factories;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import jp.cafebabe.pochi.kunai.source.DataSource;
import jp.cafebabe.pochi.kunai.source.WarFileDataSource;
import jp.cafebabe.pochi.kunai.util.PathHelper;

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
