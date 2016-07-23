package com.github.ebis.birthmarks;

import java.io.Serializable;
import java.net.URI;

public class ClassInfo implements Serializable {
    private static final long serialVersionUID = -2157060520279910519L;

    private Birthmarks birthmarks;
    private URI loadFrom;
    private ClassName name;

    public ClassInfo(ClassName name, URI loadFrom, Birthmarks birthmarks) {
        this.name = name;
        this.loadFrom = loadFrom;
        this.birthmarks = birthmarks;
    }
}
