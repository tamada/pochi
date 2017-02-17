package com.github.kunai.sink;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.Path;

import com.github.kunai.entries.Entry;
import com.github.kunai.entries.KunaiException;

public class JarFileDataSink extends AbstractDataSink {
    private FileSystem system;
    private Path base;

    public JarFileDataSink(Path path){
        this.system = DataSinkHelper.buildFileSystem(path);
        this.base = system.getPath("/");
    }

    JarFileDataSink(FileSystem system, String base){
        this.system = system;
        this.base = system.getPath(base);
    }

    @Override
    public void close() throws IOException {
        system.close();
    }

    @Override
    public void consume(InputStream in, Entry entry) throws KunaiException {
        Path outputPath = base.resolve(createPath(entry));
        DirectoryMaker.mkdirs(outputPath.getParent(), system);
        consume(in, outputPath);
    }

    private void consume(InputStream in, Path path) throws KunaiException{
        try(OutputStream out = DataSinkHelper.newOutputStream(system, path)){
            DataSinkHelper.copy(in, out);
        } catch(IOException e){
            throw new KunaiException(e.getMessage());
        }
    }

    private Path createPath(Entry entry){
        if(entry.isClass())
            return system.getPath(entry.className().toString().replace('.', '/') + ".class");
        return system.getPath(entry.path().toString());
    }
}
