package com.github.pochi.kunai.source;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import com.github.pochi.kunai.entries.ClassName;

public interface PathResolver {
    InputStream openStream(Path path) throws IOException;

    ClassName parseClassName(Path path);

    default ClassName resolveClassName(Path path) throws IOException{
        return ClassNameExtractVisitor.extractClassName(openStream(path));
    }
}
