package com.github.kunai.translators;

import com.github.kunai.entries.Entry;
import com.github.kunai.entries.KunaiException;

public interface Translator<T> {
    default T translate(Entry entry) throws KunaiException{
        supply(entry);
        return consume();
    }
    void supply(Entry entry) throws KunaiException;

    T consume() throws KunaiException;
}
