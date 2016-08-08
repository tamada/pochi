package com.github.kunai.entries;

import java.io.IOException;
import java.io.InputStream;

public interface Entry {
    public InputStream getInputStream() throws IOException;

    public ClassName getClassName();

    public boolean isName(String name);
}
