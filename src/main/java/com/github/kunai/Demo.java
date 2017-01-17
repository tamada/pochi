package com.github.kunai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.github.kunai.entries.Entry;
import com.github.kunai.source.DataSource;
import com.github.kunai.source.factories.DataSourceFactory;
import com.github.kunai.source.factories.DefaultDataSourceFactory;

public class Demo {
    public Demo(String[] args){
        Arrays.stream(args).forEach(item -> {
            try {
                listSource(item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void listSource(String item) throws Exception{
        DataSourceFactory factory = new DefaultDataSourceFactory();
        Path path = Paths.get(item);
        DataSource source = factory.build(path);

        System.out.println(path);
        source.stream().peek(entry -> {
            System.out.println(entry);
        })
        .filter(entry -> entry.isName("MANIFEST.MF"))
        .forEach(entry -> showContent(entry));
    }

    private void showContent(Entry entry){
        System.out.println(entry);
        try(BufferedReader in = new BufferedReader(new InputStreamReader(entry.openStream()))){
            in.lines().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final void main(String[] args) throws Exception{
        new Demo(args);
    }
}
