package jp.cafebabe.pochi;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Pochi {
    public static final String VERSION = "2.5.2";
    private static final Pochi INSTANCE = new Pochi();

    private final Path home;

    private Pochi() {
         home = findPochiHome();
    }

    public static final Path home() {
        return INSTANCE.home;
    }

    private static List<String> targetPaths() {
        return Arrays.asList(System.getenv("POCHI_HOME"), "/opt/pochi", "/usr/local/opt/pochi", "/opt/homebrew/opt/pochi", String.format("pochi-%s", Pochi.VERSION), ".");
    }

    private Path findPochiHome() {
        List<String> paths = targetPaths();
        Optional<String> path = paths.stream()
                .filter(p -> isExistDirectory(p))
                .findFirst();
        return Path.of(path.orElse("."));
    }

    private boolean isExistDirectory(String path) {
        if(path == null) return false;
        return Files.isDirectory(Paths.get(path));
    }
}
