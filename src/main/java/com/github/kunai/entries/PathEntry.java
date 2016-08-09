package com.github.kunai.entries;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
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
    public ClassName getClassName() {
        return source.parseClassName(path);
    }

    @Override
    public InputStream getInputStream() throws IOException{
        return source.openStream(path);
    }

    public boolean isClass(){
        String name = path.toString();
        return name.endsWith(".class");
    }
    
    public boolean isName(Name name){
        return path.endsWith(name.toString());
    }

    @Override
    public boolean isName(String name){
        return isName(new Name(name));
    }

    @Override
    public URI loadFrom(){
        return path.toUri();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(loadFrom());
        if(isClass()){
            sb.append(" <").append(getClassName()).append(">");
        }
        return new String(sb);
    }
}
