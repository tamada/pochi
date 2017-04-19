package com.github.pochi.runner.scripts.helper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.pochi.kunai.entries.KunaiException;
import com.github.pochi.kunai.source.DataSource;
import com.github.pochi.kunai.source.factories.DataSourceFactory;
import com.github.pochi.kunai.source.factories.DefaultDataSourceFactory;
import com.github.pochi.runner.birthmarks.io.DefaultDumper;

public class IOHelper {
    private PrintWriter out;

    public IOHelper(){
        this(new PrintWriter(System.out));
    }

    public IOHelper(PrintWriter out){
        this.out = out;
    }

    public DataSource open(File file) throws IOException, KunaiException{
        DataSourceFactory factory = new DefaultDataSourceFactory();
        return factory.build(file);
    }

    public DataSource open(Path path) throws IOException, KunaiException{
        DataSourceFactory factory = new DefaultDataSourceFactory();
        return factory.build(path);
    }

    public DataSource open(String path) throws IOException, KunaiException{
        return open(Paths.get(path));
    }

    public <T> void print(T set){
        DefaultDumper dumper = new DefaultDumper(out);
        dumper.print(set);
        out.flush();
    }

    public void print(String string){
        out.println(string);
        out.flush();
    }
}
