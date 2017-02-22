package com.github.pochi.kunai.source;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;
import java.util.stream.Stream;

import com.github.pochi.kunai.entries.ClassName;
import com.github.pochi.kunai.entries.Entry;
import com.github.pochi.kunai.entries.PathEntry;
import com.github.pochi.kunai.util.StreamHelper;

public class JarFileDataSource extends AbstractDataSource implements PathResolver{
    private FileSystem system;

    public JarFileDataSource(FileSystem system){
        this.system = system;
    }

    @Override
    public Stream<Entry> stream(){
        List<Path> list = getAllFilesFromPaths(getRootPaths());
        return list.stream()
                .map(path -> toEntry(path));
    }

    private List<Path> getAllFilesFromPaths(Path[] paths){
        DirectoryTraverser traverser = new DirectoryTraverser();
        FileSystemProvider provider = system.provider();
        return traverser.traverse(provider, paths);
    }

    private Path[] getRootPaths(){
        return StreamHelper
                .stream(system.getRootDirectories())
                .toArray(size -> new Path[size]);
    }

    @Override
    public ClassName parseClassName(Path path){
        String name = parseClassName(path.toString());
        return new ClassName(name);        
    }

    @Override
    public InputStream openStream(Path entry) throws IOException{
        FileSystemProvider provider = system.provider();
        return provider.newInputStream(entry);
    }

    private Entry toEntry(Path path){
        return new PathEntry(path, this);
    }

    @Override
    public void close() throws IOException{
        system.close();
    }
}
