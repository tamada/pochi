package com.github.kunai.source;

import com.github.kunai.entries.KunaiException;

public class UnsupportedDataSourceException extends KunaiException {
    private static final long serialVersionUID = -3466954463874687402L;

    public UnsupportedDataSourceException(String message){
        super(message);
    }

}
