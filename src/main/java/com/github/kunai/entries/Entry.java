package com.github.kunai.entries;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public interface Entry {
    InputStream getInputStream() throws IOException;

    ClassName getClassName();

    boolean isClass();

    boolean isName(String name);

    URI loadFrom();
}
