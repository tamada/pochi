package com.github.pochi.kunai.sink;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.github.pochi.kunai.entries.Entry;
import com.github.pochi.kunai.entries.KunaiException;
import com.github.pochi.kunai.source.DataSource;

public interface DataSink extends AutoCloseable{
    default void consume(byte[] data, Entry entry) throws KunaiException{
        consume(new ByteArrayInputStream(data), entry);
    }

    default void consume(Entry entry) throws KunaiException{
        try{ consume(entry.openStream(), entry); }
        catch(IOException e){
            throw new KunaiException(e.getMessage());
        }
    }

    void consume(InputStream in, Entry entry) throws KunaiException;

    default void consume(DataSource source){
        source.stream().forEach(entry -> { 
            try{ consume(entry); }
            catch(KunaiException e){ }
        });
    }

    @Override
    void close() throws IOException;
}
