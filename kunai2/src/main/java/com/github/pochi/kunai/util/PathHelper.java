package com.github.pochi.kunai.util;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;

import com.github.pochi.nasubi.Exceptions;

public class PathHelper {
    public static boolean endsWith(Path path, String suffix){
        String name = path.toString();
        return name.endsWith(suffix);
    }

    public static Optional<BasicFileAttributes> readAttributes(Path path){
        return readAttributes(path, FileSystems.getDefault());
    }

    public static Optional<BasicFileAttributes> readAttributes(Path givenPath, FileSystem system){
        return Exceptions.map(givenPath, system.provider(), 
                (path, provider) -> provider.readAttributes(path, BasicFileAttributes.class));
    }

    public static boolean deleteAll(Path path){
        return Exceptions.isThrowed(path, 
                dir -> Files.walkFileTree(dir, new DirectoryRemover()));
    }

    public static boolean exists(Path path){
        return exists(path, FileSystems.getDefault());
    }

    public static boolean exists(Path givenPath, FileSystem givenSystem){
        return readAttributes(givenPath, givenSystem).isPresent();
    }
}
