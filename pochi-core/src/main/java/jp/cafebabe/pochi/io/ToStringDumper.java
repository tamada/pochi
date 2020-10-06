package jp.cafebabe.pochi.io;

import java.io.PrintWriter;

public class ToStringDumper extends AbstractDumper<Object> {

    public ToStringDumper(PrintWriter out) {
        super(out);
    }

    @Override
    public void print(Object results) {
        out().println(String.valueOf(results));
    }
}
