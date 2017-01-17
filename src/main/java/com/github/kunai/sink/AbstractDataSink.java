package com.github.kunai.sink;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.github.kunai.entries.Entry;
import com.github.kunai.entries.KunaiException;
import com.github.kunai.translators.NullTranslator;
import com.github.kunai.translators.Translator;

public abstract class AbstractDataSink implements DataSink{
    private Translator<Entry> translator = new NullTranslator();

    @Override
    public final void setTranslator(Translator<Entry> translator) {
        this.translator = translator;
    }

    @Override
    public void supply(Entry entry) throws KunaiException {
        try(ByteArrayOutputStream out = new ByteArrayOutputStream()){
            translator.translate(entry);
            consume(entry, out.toByteArray());
        } catch(IOException e){
            throw new KunaiException(e.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
    }
}
