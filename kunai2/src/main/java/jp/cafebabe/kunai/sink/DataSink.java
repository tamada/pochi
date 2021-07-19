package jp.cafebabe.kunai.sink;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import jp.cafebabe.kunai.entries.Entry;
import jp.cafebabe.kunai.entries.KunaiException;
import jp.cafebabe.kunai.source.DataSource;

/**
 * This abstract class is the super class for sinking entries.
 *
 * @author Haruaki TAMADA
 */
public interface DataSink extends AutoCloseable{
    /**
     * write given data to this instance.
     * @param data  writing data
     * @param entry meta data of data
     * @throws KunaiException I/O error
     */
    default void consume(byte[] data, Entry entry) throws KunaiException{
        consume(new ByteArrayInputStream(data), entry);
    }

    /**
     * write data from the given entry to this instance.
     *
     * @param entry writing data and its meta data
     * @throws KunaiException I/O error
     */
    default void consume(Entry entry) throws KunaiException{
        try{ consume(entry.openStream(), entry); }
        catch(IOException e){
            throw new KunaiException(e.getMessage());
        }
    }

    /**
     * write given data from stream to this instance.
     * @param in writing data
     * @param entry meta data of data
     * @throws KunaiException I/O error
     */
    void consume(InputStream in, Entry entry) throws KunaiException;

    /**
     * write given data entries to this instance.
     * @param source writing data
     */
    default void consume(DataSource source){
        source.stream().forEach(entry -> { 
            try{ consume(entry); }
            catch(KunaiException e){ }
        });
    }

    /**
     * closes this instance.
     * After the calling this method, writing data of this instance causes the I/O error.
     * @throws IOException I/O error
     */
    @Override
    void close() throws IOException;
}
