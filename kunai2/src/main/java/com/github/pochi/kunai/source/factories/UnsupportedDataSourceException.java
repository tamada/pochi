package com.github.pochi.kunai.source.factories;

import com.github.pochi.kunai.entries.KunaiException;

public class UnsupportedDataSourceException extends KunaiException {
    private static final long serialVersionUID = -3466954463874687402L;

    public UnsupportedDataSourceException(String message){
        super(message);
    }

}
