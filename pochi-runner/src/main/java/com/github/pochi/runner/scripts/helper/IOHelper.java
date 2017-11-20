package com.github.pochi.runner.scripts.helper;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
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
    private String a;

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

    public <T> void writer(String path, T set) throws IOException{
        PrintWriter out = new PrintWriter(new FileWriter(path));
        DefaultDumper dumper = new DefaultDumper(out);
        dumper.print(set);
    }
    public void writer(String path, String string) throws IOException{
        PrintWriter out = new PrintWriter(new FileWriter(path,true));
        out.print(a);
        out.close();
        a=null;
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
    public void add(String string){
        a=a+string;
    }
}
