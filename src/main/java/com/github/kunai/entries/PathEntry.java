package com.github.kunai.entries;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import com.github.kunai.source.PathResolver;

public class PathEntry implements Entry{
    private Path path;
    private PathResolver source;

    public PathEntry(Path path, PathResolver source){
        this.path = path;
        this.source = source;
    }

    @Override
    public Path path(){
        return path;
    }

    @Override
    public ClassName className() {
        return source.parseClassName(path);
    }

    @Override
    public InputStream openStream() throws IOException{
        return source.openStream(path);
    }

    public boolean isName(Name name){
        return path.endsWith(name.toString());
    }

    @Override
    public boolean isName(String name){
        return isName(new Name(name));
    }

    @Override
    public String toString(){
        return String.format("%s <%s>", loadFrom(), className());
    }
}