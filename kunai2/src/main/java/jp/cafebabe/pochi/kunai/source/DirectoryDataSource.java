package jp.cafebabe.pochi.kunai.source;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import jp.cafebabe.pochi.kunai.entries.ClassName;
import jp.cafebabe.pochi.kunai.entries.Entry;
import jp.cafebabe.pochi.kunai.entries.PathEntry;

public class DirectoryDataSource extends AbstractDataSource implements PathResolver{
    private Path basePath;

    public DirectoryDataSource(Path path){
        this.basePath = path;
    }

    @Override
    public Stream<Entry> stream() {
        List<Path> list = new DirectoryTraverser().traverse(basePath);
        return list.stream()
                .map(path -> new PathEntry(path, this));
    }

    @Override
    public ClassName parseClassName(Path targetPath){
        Path path = basePath.relativize(targetPath);
        String name = path.toString();
        return new ClassName(super.parseClassName(name));
    }

    @Override
    public void close(){
        // do nothing
    }

    @Override
    public InputStream openStream(Path path) throws IOException {
        return Files.newInputStream(path);
    }
}
