package com.github.kunai.sink;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.kunai.entries.Entry;
import com.github.kunai.entries.KunaiException;

public class DirectoryDataSink extends AbstractDataSink {
    private Path base;

    public DirectoryDataSink(Path path){
        this.base = path;
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public void consume(InputStream in, Entry entry) throws KunaiException {
        Path outputPath = base.resolve(createPath(entry));
        createDirectories(outputPath.getParent());
        consume(in, outputPath);
    }

    private void createDirectories(Path path){
        try{ Files.createDirectories(path); }
        catch(IOException e){ }
    }

    private void consume(InputStream in, Path path) throws KunaiException{
        try(OutputStream out = Files.newOutputStream(path)){
            DataSinkHelper.copy(in, out);
        } catch(IOException e){
            throw new KunaiException(e.getMessage());
        }
    }

    private Path createPath(Entry entry){
        if(entry.isClass())
            return Paths.get(entry.className().toString().replace('.', '/') + ".class");
        return Paths.get(entry.path().toString());
    }
}
