package com.github.kunai.util;

import java.nio.file.Path;

public class PathHelper {
    public static boolean endsWith(Path path, String suffix){
        String name = path.toString();
        return name.endsWith(suffix);
    }
}
