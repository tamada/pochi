package com.github.ebis.birthmarks.io;

import java.io.PrintWriter;
import java.util.StringJoiner;

import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.birthmarks.entities.BirthmarkType;
import com.github.ebis.birthmarks.entities.Birthmarks;
import com.github.ebis.birthmarks.entities.ExtractionResults;
import com.github.ebis.birthmarks.entities.Metadata;

public class Dumper {
    private PrintWriter out;

    public Dumper(PrintWriter out){
        this.out = out;
    }

    public void print(ExtractionResults birthmarks){
        BirthmarkType type = birthmarks.type();
        print(type, birthmarks.birthmarks());
        out.flush();
    }

    public void print(BirthmarkType type, Birthmarks birthmarks){
        birthmarks.stream().forEach(birthmark -> print(type, birthmark));
    }

    public void print(BirthmarkType type, Birthmark birthmark){
        Metadata source = birthmark.source();
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(source.toString());
        joiner.add(type.toString());
        joiner.add(dump(birthmark));
        out.println(joiner.toString());
    }

    private String dump(Birthmark birthmark){
        StringJoiner joiner = new StringJoiner(",");
        birthmark.forEach(element -> joiner.add(element.toString()));
        return joiner.toString();
    }
}
