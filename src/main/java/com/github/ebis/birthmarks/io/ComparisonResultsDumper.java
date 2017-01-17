package com.github.ebis.birthmarks.io;

import java.io.PrintWriter;

import com.github.ebis.birthmarks.comparators.Comparison;
import com.github.ebis.birthmarks.comparators.Comparisons;
import com.github.ebis.birthmarks.entities.BirthmarkType;
import com.github.ebis.birthmarks.entities.Results;

public class ComparisonResultsDumper extends AbstractDumper<Comparisons>{
    public ComparisonResultsDumper(PrintWriter out){
        super(out);
    }

    public void print(Results<Comparisons> results){
        print(results.type(),
                results.result());
        out().flush();
    }

    public void print(BirthmarkType type, Comparisons target){
        target.forEach(comparison -> print(type, comparison));
    }

    public void print(BirthmarkType type, Comparison target){
        out().printf("%s,%s%n", type, target);
    }
}
