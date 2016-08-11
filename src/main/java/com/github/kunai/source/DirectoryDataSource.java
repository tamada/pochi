package com.github.kunai.source;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import com.github.kunai.entries.ClassName;
import com.github.kunai.entries.Entry;
import com.github.kunai.entries.PathEntry;

class DirectoryDataSource extends AbstractDataSource implements PathResolver{
    private Path basePath;

    public DirectoryDataSource(Path path){
        this.basePath = path;
    }

    @Override
    public Stream<Entry> stream() {
        DirectoryTraverser traverser = new DirectoryTraverser();
        List<Path> list = traverser.traverse(basePath);

        return list.stream().map(path -> new PathEntry(path, this));
    }

    @Override
    public ClassName parseClassName(Path targetPath){
        Path path = basePath.relativize(targetPath);
        String name = path.toString();
        int lastIndex = getLastIndex(name, ".class");
        name = name.substring(0, lastIndex);

        return new ClassName(name);
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
