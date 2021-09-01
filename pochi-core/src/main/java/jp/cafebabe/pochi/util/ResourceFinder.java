package jp.cafebabe.pochi.util;

import io.vavr.control.Try;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class ResourceFinder {
    private ResourceFinder() {
    }

    private static Optional<URL> findFromPath(String from) {
        Path path = Path.of(from);
        if(Files.exists(path))
            return Optional.ofNullable(toURL(path));
        return Optional.empty();
    }

    private static URL toURL(Path path) {
        return Try.of(() -> path.toUri().toURL())
                .get();
    }

    private static Optional<URL> findFromClassLoader(String name) {
        return Optional.ofNullable(ResourceFinder.class
                .getResource(name));
    }

    private static Optional<URL> findAsURL(String name) {
        return Try.of(() -> new URL(name))
                .toJavaOptional();
    }

    public static Optional<URL> find(String from) {
        if(from == null)
            return Optional.empty();
        return findFromPath(from)
                .or(() -> findFromClassLoader(from))
                .or(() -> findAsURL(from));
    }
}
