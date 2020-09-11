package jp.cafebabe.kunai.source;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import jp.cafebabe.kunai.entries.ClassName;

public interface PathResolver {
    InputStream openStream(Path path) throws IOException;

    ClassName parseClassName(Path path);

    default ClassName resolveClassName(Path path) throws IOException{
        return ClassNameExtractVisitor.extractClassName(openStream(path));
    }
}
