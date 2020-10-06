package jp.cafebabe.pochi.io;

import java.io.PrintWriter;

import jp.cafebabe.birthmarks.comparators.Comparison;
import jp.cafebabe.birthmarks.comparators.Comparisons;

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
