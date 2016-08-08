package com.github.kunai.entries;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import com.github.kunai.source.DefaultDataSource;

public class PathEntry implements Entry{
    private Path path;
    private DefaultDataSource source;

    public PathEntry(Path path, DefaultDataSource source){
        this.path = path;
        this.source = source;
    }

    @Override
    public InputStream getInputStream() throws IOException{
        return source.openStream(path);
    }

    @Override
    public ClassName getClassName() {
        return ClassName.parse(path);
    }

    public boolean isName(String name){
        return isName(new Name(name));
    }

    public boolean isName(Name name){
        return path.endsWith(name.toString());
    }

    public String toString(){
        return path.toAbsolutePath().toString();
    }
}
