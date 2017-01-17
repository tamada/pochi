package com.github.ebis.birthmarks.io;

import java.io.PrintWriter;
import java.util.StringJoiner;

import com.github.ebis.birthmarks.entities.Birthmark;
import com.github.ebis.birthmarks.entities.BirthmarkType;
import com.github.ebis.birthmarks.entities.Birthmarks;
import com.github.ebis.birthmarks.entities.Metadata;
import com.github.ebis.birthmarks.entities.Results;

public class ExtractionResultsDumper extends AbstractDumper<Birthmarks>{
    public ExtractionResultsDumper(PrintWriter out) {
        super(out);
    }

    public void print(Results<Birthmarks> birthmarks) {
        BirthmarkType type = birthmarks.type();
        print(type, birthmarks.result());
        out().flush();
    }

    public void print(BirthmarkType type, Birthmarks birthmarks) {
        birthmarks.stream()
        .forEach(birthmark -> print(type, birthmark));
    }

    public void print(BirthmarkType type, Birthmark birthmark) {
        Metadata source = birthmark.metadata();
        out().printf("%s,%s,%s%n", source, type, dump(birthmark));
    }

    private String dump(Birthmark birthmark) {
        StringJoiner joiner = new StringJoiner(",");
        birthmark.forEach(element -> joiner.add(element.toString()));
        return joiner.toString();
    }
}
