package com.github.kunai.sink;

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

public class DataSinkHelper {
    public static FileSystem buildFileSystem(Path path){
        Map<String, String> environment = new HashMap<>();
        environment.put("create", "true");
        return buildFileSystem(path, environment);
    }

    public static FileSystem buildFileSystem(Path path, Map<String, String> environment){
        try {
            return FileSystems.newFileSystem(URI.create("jar:file:" + path.toAbsolutePath()), environment);
        } catch (IOException e) { e.printStackTrace(); }
        return null;
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
