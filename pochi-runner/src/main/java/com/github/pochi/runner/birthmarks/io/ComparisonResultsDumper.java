package com.github.pochi.runner.birthmarks.io;

import java.io.PrintWriter;

import com.github.pochi.runner.birthmarks.comparators.Comparison;
import com.github.pochi.runner.birthmarks.comparators.Comparisons;
import com.github.pochi.runner.birthmarks.entities.BirthmarkType;
import com.github.pochi.runner.birthmarks.entities.Results;

public class ComparisonResultsDumper extends AbstractDumper<Comparisons>{
    public ComparisonResultsDumper(PrintWriter out){
        super(out);
    }

    public void print(Results<Comparisons> results){
        print(results.type(),
                results.result());
        flush();
    }

    public void print(BirthmarkType type, Comparisons target){
        target.forEach(comparison -> print(type, comparison));
    }

    public void print(BirthmarkType type, Comparison target){
        out().printf("%s,%s%n", type, target);
    }
}
