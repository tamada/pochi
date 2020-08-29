package jp.cafebabe.pochi.kunai.source;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;
import java.util.stream.Stream;

import jp.cafebabe.pochi.kunai.entries.ClassName;
import jp.cafebabe.pochi.kunai.entries.Entry;
import jp.cafebabe.pochi.kunai.entries.PathEntry;
import jp.cafebabe.pochi.kunai.util.StreamHelper;

public class JarFileDataSource extends AbstractDataSource implements PathResolver{
    private FileSystem system;

    public JarFileDataSource(Path path, FileSystem system) {
        super(path);
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
