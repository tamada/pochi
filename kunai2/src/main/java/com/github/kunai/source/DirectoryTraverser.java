package com.github.kunai.source;

import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.github.kunai.util.Exceptions;

class DirectoryTraverser {
    private static final DirectoryStream.Filter<Path> FILTER = new EveryFileAcceptFilter();

    public List<Path> traverse(Path... paths){
        FileSystem system = FileSystems.getDefault();
        return traverse(system.provider(), new ArrayList<>(), paths);
    }

    public List<Path> traverse(FileSystemProvider provider, Path... paths){
        return traverse(provider, new ArrayList<>(), paths);
    }

    private List<Path> traverse(FileSystemProvider provider, List<Path> list, Path... paths){
        Arrays.stream(paths)
        .forEach(path -> traverse(provider, list, path));
        return Collections.unmodifiableList(list);
    }

    private List<Path> traverse(FileSystemProvider provider, List<Path> list, Path path){
        try(DirectoryStream<Path> stream = provider.newDirectoryStream(path, FILTER)){
            stream.forEach(p -> traverseDirectory(provider, list, p));
        } catch (Exception e){ }
        return list;
    }

    private void traverseDirectory(FileSystemProvider provider, List<Path> list, Path path){
        Optional<BasicFileAttributes> optional = getAttributes(provider, path);
        optional.ifPresent(attr -> doTraverse(provider, list, path, attr));
    }

    private void doTraverse(FileSystemProvider provider, List<Path> list, Path path, BasicFileAttributes attr){
        if(attr.isDirectory()){
            traverse(provider, list, path);
            return;
        }
        list.add(path);
    }

    private Optional<BasicFileAttributes> getAttributes(FileSystemProvider provider, Path path){
        return Exceptions.map(provider, path, (p2, path2) -> 
            provider.readAttributes(path, BasicFileAttributes.class));
    }
}
