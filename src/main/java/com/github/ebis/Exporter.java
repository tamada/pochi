package com.github.ebis;

import java.io.IOException;
import java.io.OutputStream;

public interface Exporter {
    void export(OutputStream out) throws IOException;
}
