package com.github.kunai.sink;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;

import com.github.kunai.util.Exceptions;

public class DirectoryMaker {
    private static boolean exists(FileSystemProvider provider, Path path){
        return Exceptions.isThrowed(provider, path, 
                (givenProvider, givenPath) -> givenProvider.readAttributes(
                        givenPath, BasicFileAttributes.class, NOFOLLOW_LINKS));
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
