package com.github.pochi.kunai.source;

public abstract class AbstractDataSource implements DataSource{
    int getLastIndex(String name, String extension){
        return name.lastIndexOf(extension);
    }

    int getStartIndex(String name){
        int startIndex = 0;
        if(name.startsWith("/")) startIndex = 1;
        return startIndex;
    }

    String parseClassName(String name){
        int start = getStartIndex(name);
        int last = getLastIndex(name, ".class");
        return trimName(name, start, last);
    }

    String trimName(String name, int start, int last){
        if(start >= 0 && start < last)
            return name.substring(start, last);
        return name;
    }
}
