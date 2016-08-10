package com.github.kunai.translators;

import java.io.IOException;
import java.io.OutputStream;

import com.github.kunai.entries.Entry;

public interface Translator {
    default void translate(Entry entry, OutputStream out) throws IOException{
        supply(entry);
        consume(out);
    }
    void supply(Entry entry) throws IOException;

    void consume(OutputStream out) throws IOException;
}
