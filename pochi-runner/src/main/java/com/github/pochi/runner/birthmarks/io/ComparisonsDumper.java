package com.github.pochi.runner.birthmarks.io;

import java.io.PrintWriter;

import com.github.pochi.runner.birthmarks.comparators.Comparison;
import com.github.pochi.runner.birthmarks.comparators.Comparisons;

public class ComparisonsDumper extends AbstractDumper<Comparisons>{
    public ComparisonsDumper(PrintWriter out){
        super(out);
    }

    public void print(Comparisons target){
        target.forEach(this::print);
        flush();
    }

    public void print(Comparison target){
        out().printf("%s%n", target);
    }
}
