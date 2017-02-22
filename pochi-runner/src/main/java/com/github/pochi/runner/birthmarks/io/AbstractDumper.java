package com.github.pochi.runner.birthmarks.io;

import java.io.PrintWriter;

public abstract class AbstractDumper<T> implements Dumper<T>{
    private PrintWriter out;

    public AbstractDumper(PrintWriter out){
        this.out = out;
    }

    PrintWriter out(){
        return out;
    }

    void flush(){
        out.flush();
    }
}
