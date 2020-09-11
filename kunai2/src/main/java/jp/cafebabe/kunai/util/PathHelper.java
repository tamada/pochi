package jp.cafebabe.kunai.util;

import io.vavr.control.Try;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.Optional;

public class PathHelper {
    public static boolean endsWith(Path path, String suffix){
        String name = path.toString();
        return name.endsWith(suffix);
    }

    public static Optional<BasicFileAttributes> readAttributes(Path path){
        return readAttributes(path, FileSystems.getDefault());
    }

    public static Optional<BasicFileAttributes> readAttributes(Path givenPath, FileSystemProvider provider) {
        return Try.of(() -> provider.readAttributes(givenPath, BasicFileAttributes.class))
                .toJavaOptional();

    }

    public static Optional<BasicFileAttributes> readAttributes(Path givenPath, FileSystem system){
        return readAttributes(givenPath, system.provider());
    }

    public static boolean deleteAll(Path path){
        return Try.of(() -> Files.walkFileTree(path, new DirectoryRemover()))
                .isSuccess();
    }

    public static boolean exists(Path path){
        return exists(path, FileSystems.getDefault());
    }

    public static boolean exists(Path givenPath, FileSystem givenSystem){
        return readAttributes(givenPath, givenSystem).isPresent();
    }
}
