package com.github.pochi.runner.birthmarks.io;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.github.pochi.birthmarks.comparators.Comparisons;
import com.github.pochi.birthmarks.entities.Birthmarks;

public class DefaultDumper{
    private Map<String, Dumper<?>> map = new HashMap<>();

    public DefaultDumper(PrintWriter out){
        map.put(Birthmarks.class.getName(), new BirthmarksDumper(out));
        map.put(Comparisons.class.getName(), new ComparisonsDumper(out));
    }

    public <T> void print(T target){
        Dumper<T> dumper = obtainDumper(target);
        dumper.print(target);
    }

    @SuppressWarnings("unchecked")
    private <T> Dumper<T> obtainDumper(Object target){
        String name = target.getClass()
                .getName();
        return (Dumper<T>)map.get(name);
    }
}
