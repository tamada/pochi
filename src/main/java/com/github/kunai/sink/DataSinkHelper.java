package com.github.kunai.sink;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashMap;
import java.util.Map;

public class DataSinkHelper {
    public static FileSystem buildFileSystem(Path path){
        try {
            Map<String, String> environment = new HashMap<>();
            environment.put("create", "true");
            System.out.println("jar:" + path.toAbsolutePath().toUri());
            return FileSystems.newFileSystem(URI.create("jar:file:" + path.toAbsolutePath()), environment);
        } catch (IOException e) { e.printStackTrace(); }
        return null;
    }

    private static boolean exists(FileSystemProvider provider, Path path){
        try{
            provider.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
            return true;
        } catch(IOException e){ return false; }
    }

    public static void createDirectories(FileSystem system, Path path){
        FileSystemProvider provider = system.provider();
        
    }

    private void createDirectoriesImpl(FileSystemProvider provider, Path path) throws IOException{
        while(path != null){
            if(!exists(provider, path)){
                createDirectoriesImpl(provider, path);
                provider.createDirectory(path);
            }
            path = path.getParent();
        }
    }

    public static void copy(InputStream in, OutputStream out) throws IOException{
        int data;
        while((data = in.read()) != -1)
            out.write(data);
    }
}
