package com.github.kunai.source;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.github.kunai.entries.Entry;
import com.github.kunai.entries.PathEntry;

public class DefaultDataSource implements DataSource{
    private FileSystem system;

    public DefaultDataSource(FileSystem system){
        this.system = system;
    }

    @Override
    public Stream<Entry> stream(){
        Iterable<Path> iterable = system.getRootDirectories();
        Spliterator<Path> spliterator = iterable.spliterator();

        List<Path> paths = new ArrayList<>();
        FileSystemProvider provider = system.provider();

        StreamSupport
        .stream(spliterator, false)
        .forEach(path -> traverse(provider, paths, path));

        return paths.stream().map(path -> convertToEntry(path));
    }

    private void traverse(FileSystemProvider provider, List<Path> list, Path path){
        try(DirectoryStream<Path> stream = provider.newDirectoryStream(path, null)){
            stream.forEach(p -> {
                traverseDirectory(provider, list, p);
            });
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    private void traverseDirectory(FileSystemProvider provider, List<Path> list, Path path){
        try {
            BasicFileAttributes attr = provider.readAttributes(path, BasicFileAttributes.class);
            if(attr.isDirectory()){
                traverse(provider, list, path);
                return;
            }
            list.add(path);
        } catch (IOException e) {
            // e.printStackTrace();
        }
        
    }

    public InputStream openStream(Path entry) throws IOException{
        FileSystemProvider provider = system.provider();
        return provider.newInputStream(entry);
    }

    private Entry convertToEntry(Path path){
        return new PathEntry(path, this);
    }

    @Override
    public void close() throws IOException{
        system.close();
    }
}
