package com.github.kunai.source;

public abstract class AbstractDataSource implements DataSource{
    int getLastIndex(String name, String extension){
        return name.lastIndexOf(extension);
    }

    int getStartIndex(String name){
        int startIndex = 0;
        if(name.startsWith("/")){
            startIndex = 1;
        }
        return startIndex;
    }
}
