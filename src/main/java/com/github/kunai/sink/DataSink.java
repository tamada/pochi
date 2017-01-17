package com.github.kunai.sink;

import java.io.IOException;

import com.github.kunai.entries.Entry;
import com.github.kunai.entries.KunaiException;
import com.github.kunai.translators.Translator;

public interface DataSink extends AutoCloseable{
    void setTranslator(Translator<Entry> translator);

    void supply(Entry entry) throws KunaiException;

    void consume(Entry entry, byte[] data) throws KunaiException;

    @Override
    void close() throws IOException;
}
