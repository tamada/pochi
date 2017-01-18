package com.github.kunai.sink;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;

import com.github.kunai.entries.Entry;
import com.github.kunai.entries.KunaiException;

public class JarFileDataSink extends AbstractDataSink {
    private FileSystem system;
    private Path base;

    public JarFileDataSink(Path path){
        this(DataSinkHelper.buildFileSystem(path));
    }

    public JarFileDataSink(FileSystem system){
        this.system = system;
        this.base = system.getPath("/");
    }

    @Override
    public void close() throws IOException {
        system.close();
    }

    @Override
    public void consume(InputStream in, Entry entry) throws KunaiException {
        Path newPath = base.resolve(createPath(entry));
        consume(in, base.resolve(newPath));
    }

    private void consume(InputStream in, Path outputPath) throws KunaiException{
        DataSinkHelper.createDirectories(system, outputPath.getParent());
        System.out.println("output: " + outputPath);
        try(OutputStream out = newOutputStream(outputPath)){
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

    private OutputStream newOutputStream(Path path) throws IOException{
        FileSystemProvider provider = system.provider();
        return provider.newOutputStream(path);
    }
}
