package com.github.kunai.translators;

import com.github.kunai.entries.Entry;
import com.github.kunai.entries.KunaiException;

public class NullTranslator implements Translator<Entry> {
    private Entry entry;

    @Override
    public void supply(Entry entry) throws KunaiException {
        this.entry = entry;
    }

    @Override
    public Entry consume() throws KunaiException {
        return entry;
    }
}
