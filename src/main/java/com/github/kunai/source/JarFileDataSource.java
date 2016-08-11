package com.github.kunai.source;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.github.kunai.entries.ClassName;
import com.github.kunai.entries.Entry;
import com.github.kunai.entries.PathEntry;

class JarFileDataSource extends AbstractDataSource implements PathResolver{
    private FileSystem system;

    public JarFileDataSource(FileSystem system){
        this.system = system;
    }

    @Override
    public Stream<Entry> stream(){
        Path[] paths = getRootPaths();
        List<Path> list = getAllFilesFromPaths(paths);
        
        return list.stream()
                .map(path -> mapToEntry(path));
    }

    private List<Path> getAllFilesFromPaths(Path[] paths){
        DirectoryTraverser traverser = new DirectoryTraverser();
        FileSystemProvider provider = system.provider();
        return traverser.traverse(provider, paths);
    }

    private Path[] getRootPaths(){
        Iterable<Path> iterable = system.getRootDirectories();
        Spliterator<Path> spliterator = iterable.spliterator();

        return StreamSupport.stream(spliterator, false).toArray(Path[]::new);
    }

    @Override
    public ClassName parseClassName(Path path){
        String name = path.toString();
        int startIndex = getStartIndex(name);
        int lastIndex = getLastIndex(name, ".class");
        name = name.substring(startIndex, lastIndex);

        return new ClassName(name);        
    }

    @Override
    public InputStream openStream(Path entry) throws IOException{
        FileSystemProvider provider = system.provider();
        return provider.newInputStream(entry);
    }

    private Entry mapToEntry(Path path){
        return new PathEntry(path, this);
    }

    @Override
    public void close() throws IOException{
        system.close();
    }
}
