package com.github.kunai.source;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import com.github.kunai.entries.ClassName;

public interface PathResolver {
    InputStream openStream(Path path) throws IOException;

    ClassName parseClassName(Path path);
}
