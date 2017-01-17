package com.github.ebis.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Resource {
    private String resourcePath;

    public Resource(String resourcePath){
        this.resourcePath = resourcePath;
    }

    public void print(PrintWriter out){
        try(BufferedReader in = openStream()){
            printAll(out, in);
        } catch (IOException e) { }
    }

    private void printAll(PrintWriter out, BufferedReader in) throws IOException{
        in.lines()
        .forEach(out::println);
        out.flush();
    }

    private BufferedReader openStream(){
        InputStream in = getClass().getResourceAsStream(resourcePath);
        return new BufferedReader(new InputStreamReader(in));
    }
}
