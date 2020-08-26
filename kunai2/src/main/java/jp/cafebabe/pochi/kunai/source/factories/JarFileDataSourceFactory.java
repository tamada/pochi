package jp.cafebabe.pochi.kunai.source.factories;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import jp.cafebabe.pochi.kunai.entries.KunaiException;
import jp.cafebabe.pochi.kunai.source.DataSource;
import jp.cafebabe.pochi.kunai.source.JarFileDataSource;
import jp.cafebabe.pochi.kunai.util.PathHelper;

class JarFileDataSourceFactory implements DataSourceFactory{
    public JarFileDataSourceFactory(){
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes){
        return PathHelper.endsWith(path, ".jar")
                && attributes.isRegularFile();
    }

    @Override
    public DataSource build(Path path, FileSystem system) throws KunaiException{
        try{ return buildImpl(path, system); }
        catch(IOException e){
            throw new UnsupportedDataSourceException(e.getMessage()); 
        }
    }

    private DataSource buildImpl(Path path, FileSystem system) throws KunaiException, IOException{
        if(!isTarget(path, system))
            throw new UnsupportedDataSourceException(path + ": not supported");
        return buildDataSourceImpl(path, system);
    }

    private DataSource buildDataSourceImpl(Path path, FileSystem system) throws IOException{
        ClassLoader loader = getClass().getClassLoader();
        FileSystem jarSystem = FileSystems.newFileSystem(path, loader);
        return buildDataSource(path, jarSystem);
    }

    DataSource buildDataSource(Path base, FileSystem system){
        return new JarFileDataSource(base, system);
    }
}
