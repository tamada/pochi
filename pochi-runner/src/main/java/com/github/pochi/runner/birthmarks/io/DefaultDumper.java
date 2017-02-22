package com.github.pochi.runner.birthmarks.io;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.github.pochi.runner.birthmarks.comparators.Comparisons;
import com.github.pochi.runner.birthmarks.entities.BirthmarkType;
import com.github.pochi.runner.birthmarks.entities.Birthmarks;
import com.github.pochi.runner.birthmarks.entities.Results;

public class DefaultDumper{
    private Map<String, Dumper<?>> map = new HashMap<>();

    public DefaultDumper(PrintWriter out){
        map.put(Birthmarks.class.getName(), new ExtractionResultsDumper(out));
        map.put(Comparisons.class.getName(), new ComparisonResultsDumper(out));
    }

    public <T> void print(BirthmarkType type, T target){
        Dumper<T> dumper = obtainDumper(target);
        dumper.print(type, target);
    }

    @SuppressWarnings("unchecked")
    private <T> Dumper<T> obtainDumper(Object target){
        String name = target.getClass()
                .getName();
        return (Dumper<T>)map.get(name);
    }

    public <T> void print(Results<T> results) {
        Dumper<T> dumper = obtainDumper(results.result());
        dumper.print(results);
    }
}
