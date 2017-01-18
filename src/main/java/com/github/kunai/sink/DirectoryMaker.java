package com.github.kunai.sink;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;

public class DirectoryMaker {
    private static boolean exists(FileSystemProvider provider, Path path){
        try{
            provider.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
            return true;
        } catch(IOException e){ return false; }
    }

    public static void mkdirs(FileSystem system, Path path){
        try{
            mkdirsImpl(system.provider(), path.getParent());
        } catch(IOException e){ }
    }

    private static void mkdirsImpl(FileSystemProvider provider, Path path) throws IOException{
        if(path == null || exists(provider, path)) return;
        mkdirsImpl(provider, path.getParent());
        provider.createDirectory(path);
    }
}
