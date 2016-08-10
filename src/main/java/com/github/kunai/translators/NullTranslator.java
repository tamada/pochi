package com.github.kunai.translators;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.github.kunai.entries.Entry;

public class NullTranslator implements Translator {
    private Entry entry;

    @Override
    public void supply(Entry entry) throws IOException {
        this.entry = entry;
    }

    @Override
    public void consume(OutputStream out) throws IOException {
        try(InputStream in = entry.getInputStream()){
            bridge(in, out);
        }
    }

    private void bridge(InputStream in, OutputStream out) throws IOException{
        int data;
        while((data = in.read()) != -1){
            out.write(data);
        }
    }
}
