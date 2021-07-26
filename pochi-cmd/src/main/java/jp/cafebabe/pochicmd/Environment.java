package jp.cafebabe.pochicmd;

import jp.cafebabe.pochi.Pochi;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Environment {
    private Classpath classpath = new Classpath();

    public String classpath(Arguments args) {
        classpath.appendPochiClasspath(this);
        classpath.appendUserClasspath(args);
        return classpath.toString();
    }

    public Path pochiHome() {
        return Pochi.home();
    }
}
