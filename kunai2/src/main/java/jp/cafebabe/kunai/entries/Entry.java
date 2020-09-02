package jp.cafebabe.kunai.entries;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Path;

public interface Entry {
    InputStream openStream() throws IOException;

    ClassName className();

    default boolean isClass(){
        String name = path().toString();
        return name.endsWith(".class");
    }

    boolean endsWith(String suffix);

    boolean isName(String name);

    default URI loadFrom(){
        return path().toUri();
    }

    Path path();
}
