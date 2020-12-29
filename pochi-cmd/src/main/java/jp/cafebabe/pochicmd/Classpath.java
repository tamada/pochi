package jp.cafebabe.pochicmd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Classpath {
    private List<String> list = new ArrayList<>();

    public String toString() {
        return list.stream()
                .collect(Collectors.joining(":"));
    }

    public void appendUserClasspath(Arguments args) {
        args.classpaths()
                .forEach(list::add);
    }

    public void appendPochiClasspath(Environment env) {
        Path home = env.pochiHome();
        try (Stream<Path> stream = Files.list(home.resolve("lib"))) {
            stream.filter(path -> Files.isRegularFile(path) && jarOrWarOrZip(path))
                    .map(path -> path.toString())
                    .forEach(list::add);
        } catch (IOException e) {
        }
    }

    private boolean jarOrWarOrZip(Path path) {
        String name = path.getFileName().toString();
        return name.endsWith(".jar") || name.endsWith(".war") || name.endsWith(".zip");
    }
}
