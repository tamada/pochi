package com.github.kunai.entries;

import java.nio.file.Path;


public class ClassName extends Name{
    private static final long serialVersionUID = -1305904437135129418L;

    public ClassName(String name){
        super(name);
    }

    public static ClassName parse(Path path){
        String name = path.toString();
        if(name.startsWith("/")){
            int lastIndex = name.length();
            lastIndex = lastIndex - ".class".length();
            name = name.substring(1, lastIndex);
        }
        return new ClassName(name.replace('/', '.'));
    }
}
