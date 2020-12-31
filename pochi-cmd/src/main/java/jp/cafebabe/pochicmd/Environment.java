package jp.cafebabe.pochicmd;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Environment {
    private Path pochiHome = null;
    private Classpath classpath = new Classpath();

    public String classpath(Arguments args) {
        classpath.appendPochiClasspath(this);
        classpath.appendUserClasspath(args);
        return classpath.toString();
    }

    public Path pochiHome() {
        if (pochiHome == null) {
            pochiHome = findPochiHome();
        }
        return pochiHome;
    }

    private List<String> targetPaths() {
        return Arrays.asList(System.getenv("POCHI_HOME"), "/opt/pochi", "/usr/local/opt/pochi", String.format("pochi-%s", Main.VERSION));
    }

    private Path findPochiHome() {
        List<String> paths = targetPaths();
        Optional<String> path = paths.stream()
                .filter(p -> isExistDirectory(p))
                .findFirst();
        return Paths.get(path.orElse("."));
    }

    private boolean isExistDirectory(String path) {
        if(path == null) return false;
        return Files.isDirectory(Paths.get(path));
    }
}
