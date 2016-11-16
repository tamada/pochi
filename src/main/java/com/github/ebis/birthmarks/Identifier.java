package com.github.ebis.birthmarks;

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;

import com.github.kunai.entries.ClassName;

public class Identifier implements Serializable {
    private static final long serialVersionUID = -2157060520279910519L;

    private URI loadFrom;
    private ClassName name;

    public Identifier(ClassName name, URI loadFrom) {
        this.name = name;
        this.loadFrom = loadFrom;
    }

    public ClassName className(){
        return name;
    }

    public boolean isSameClassName(ClassName cn){
        return Objects.equals(cn, name);
    }
    
    public boolean isSameClassName(Identifier identifier){
        return isSameClassName(identifier.className());
    }

    public boolean isSameLocation(Identifier identifier){
        return loadFrom().equals(identifier.loadFrom());
    }

    public URI loadFrom() {
        return loadFrom;
    }

    public ClassName name() {
        return name;
    }
}
