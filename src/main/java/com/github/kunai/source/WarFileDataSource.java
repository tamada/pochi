package com.github.kunai.source;

import java.nio.file.FileSystem;

public class WarFileDataSource extends JarFileDataSource{
    private static final String WAR_CLASSES_PREFIX = "/WEB-INF/classes/";
    public WarFileDataSource(FileSystem system){
        super(system);
    }

    @Override
    int getStartIndex(String name){
        if(name.startsWith(WAR_CLASSES_PREFIX)){
            return WAR_CLASSES_PREFIX.length();
        }
        return 0;
    }
}
