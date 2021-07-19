package jp.cafebabe.kunai.source.factories;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import jp.cafebabe.kunai.entries.KunaiException;
import jp.cafebabe.kunai.source.JarFileDataSource;
import jp.cafebabe.kunai.util.PathHelper;
import jp.cafebabe.kunai.source.DataSource;

class JarFileDataSourceFactory implements DataSourceFactory{
    public JarFileDataSourceFactory(){
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes){
        return PathHelper.endsWith(path, ".jar")
                && attributes.isRegularFile();
    }

    @Override
    public DataSource build(Path path, FileSystem system) throws KunaiException {
        try{ return buildImpl(path, system); }
        catch(IOException e){
            throw new UnsupportedDataSourceException(e.getMessage()); 
        }
    }

    private DataSource buildImpl(Path path, FileSystem system) throws KunaiException, IOException{
        if(!isTarget(path, system))
            throw new UnsupportedDataSourceException(path + ": not supported");
        return buildDataSourceImpl(path);
    }

    private DataSource buildDataSourceImpl(Path path) throws IOException{
        ClassLoader loader = getClass().getClassLoader();
        FileSystem jarSystem = FileSystems.newFileSystem(path, loader);
        return buildDataSource(path, jarSystem);
    }

    DataSource buildDataSource(Path base, FileSystem system){
        return new JarFileDataSource(base, system);
    }
}
