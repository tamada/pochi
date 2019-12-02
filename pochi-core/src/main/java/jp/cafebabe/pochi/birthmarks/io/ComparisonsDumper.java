package jp.cafebabe.pochi.birthmarks.io;

import java.io.PrintWriter;

import jp.cafebabe.pochi.birthmarks.comparators.Comparison;
import jp.cafebabe.pochi.birthmarks.comparators.Comparisons;

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
