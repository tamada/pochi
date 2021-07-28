package jp.cafebabe.pochi.io;

import java.io.PrintWriter;

import jp.cafebabe.birthmarks.comparators.Comparison;
import jp.cafebabe.birthmarks.comparators.Comparisons;

public class ComparisonsDumper<T> extends AbstractDumper<Comparisons<T>>{
    public ComparisonsDumper(PrintWriter out){
        super(out);
    }

    public void print(Comparisons<T> target){
        target.forEach(this::print);
        flush();
    }

    public void print(Comparison<T> target){
        out().printf("%s%n", target);
    }
}
