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

    private Path findPochiHome() {
        List<String> paths = Arrays.asList(System.getenv("POCHI_HOME"), "/opt/pochi", "/usr/local/opt/pochi", String.format("pochi-%s", Main.VERSION));
        Optional<Path> path = paths.stream()
                .filter(p -> p != null)
                .map(p -> Paths.get(p))
                .filter(p -> Files.isDirectory(p))
                .findFirst();
        return path.orElseGet(() -> Paths.get("."));
    }
}
