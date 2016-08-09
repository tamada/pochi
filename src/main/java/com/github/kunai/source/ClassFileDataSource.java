package com.github.kunai.source;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.github.kunai.entries.ClassName;
import com.github.kunai.entries.Entry;
import com.github.kunai.entries.PathEntry;

public class ClassFileDataSource extends AbstractDataSource implements PathResolver{
    private Path path;
    private ClassName name = null;

    public ClassFileDataSource(Path path){
        this.path = path;
    }

    @Override
    public Stream<Entry> stream() {
        return Stream.of(new PathEntry(path, this));
    }

    @Override
    public void close() throws IOException {
        // do nothing.
    }

    @Override
    public InputStream openStream(Path path) throws IOException {
        return Files.newInputStream(path);
    }

    @Override
    public ClassName parseClassName(Path path) {
        if(name == null){
            name = extractClassName(path);
        }
        return name;
    }

    private ClassName extractClassName(Path path){
        try{
            return this.resolveClassName(path);
        } catch(IOException e){
        }
        return null;
    }
}
