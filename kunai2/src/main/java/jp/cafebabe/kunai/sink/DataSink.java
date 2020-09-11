package jp.cafebabe.kunai.sink;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import jp.cafebabe.kunai.entries.Entry;
import jp.cafebabe.kunai.entries.KunaiException;
import jp.cafebabe.kunai.source.DataSource;

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
