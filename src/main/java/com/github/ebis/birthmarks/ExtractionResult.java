package com.github.ebis.birthmarks;

import com.github.kunai.entries.ClassName;

public class ExtractionResult<T> {
    private Identifier identifier;
    private Birthmark<T> birthmark;

    public ExtractionResult(Identifier identifier, Birthmark<T> birthmark){
        this.identifier = identifier;
        this.birthmark = birthmark;
    }

    public boolean isSameClassName(ClassName name){
        return identifier.isSameClassName(name);
    }

    public boolean isSameClassName(ExtractionResult<T> er){
        return identifier.isSameClassName(er.identifier);
    }

    public boolean isSameLocation(ExtractionResult<T> er){
        return identifier.isSameLocation(er.identifier);
    }

    public Birthmark<T> birthmark(){
        return birthmark;
    }

    public Identifier identifier(){
        return identifier;
    }
}
