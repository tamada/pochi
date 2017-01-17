package com.github.ebis.birthmarks.entities;

import java.util.Objects;

public class ExtractionResults {
    private Birthmarks birthmarks;
    private BirthmarkType birthmarkType;

    public ExtractionResults(BirthmarkType type, Birthmarks birthmarks){
        this.birthmarkType = type;
        this.birthmarks = birthmarks;
    }

    public Birthmarks birthmarks(){
        return birthmarks;
    }

    public boolean isSameType(BirthmarkType type){
        return Objects.equals(birthmarkType, type);
    }

    public BirthmarkType type(){
        return birthmarkType;
    }
}
