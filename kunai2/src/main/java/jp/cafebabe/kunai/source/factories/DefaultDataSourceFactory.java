package jp.cafebabe.kunai.source.factories;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;

import jp.cafebabe.kunai.entries.KunaiException;
import jp.cafebabe.kunai.source.DataSource;

public class DefaultDataSourceFactory implements DataSourceFactory{
    private DataSourceFactories factories = new DataSourceFactories();

    @Override
    public DataSource build(Path path, FileSystem system) throws KunaiException {
        try{
            return build(factories.find(path, system), path);
        } catch (IOException e) {
            throw new KunaiException(e.getLocalizedMessage());
        }
    }

    private DataSource build(Optional<DataSourceFactory> source, Path path) throws KunaiException{
        return source.orElseThrow(
                () -> new UnsupportedDataSourceException(path.toString()))
                .build(path);
    }

    @Override
    public boolean isTarget(Path path, FileSystem system, BasicFileAttributes attributes) {
        return path != null;
    }
}
