package com.github.pochi.kunai.sink;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.github.pochi.nasubi.Exceptions;

public class DataSinkHelper {
    public static FileSystem buildFileSystem(Path path){
        Map<String, String> environment = new HashMap<>();
        environment.put("create", "true");
        return buildFileSystem(path, environment).get();
    }

    private static Optional<FileSystem> buildFileSystem(Path path, Map<String, String> environment){
        return Exceptions.map(path, environment,
                (p, map) -> FileSystems.newFileSystem(
                        URI.create("jar:file:" + p.toAbsolutePath()), map));
    }

    public static OutputStream newOutputStream(FileSystem system, Path path) throws IOException{
        FileSystemProvider provider = system.provider();
        return provider.newOutputStream(path);
    }

    public static void copy(InputStream in, OutputStream out) throws IOException{
        int data;
        while((data = in.read()) != -1)
            out.write(data);
    }
}
