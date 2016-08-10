package com.github.kunai.entries;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Path;

public interface Entry {
    InputStream getInputStream() throws IOException;

    ClassName getClassName();

    boolean isClass();

    boolean isName(String name);

    URI loadFrom();

    Path getPath();
}
