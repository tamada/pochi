package com.github.kunai.source;

import java.nio.file.DirectoryStream.Filter;
import java.nio.file.Path;

public class EveryFileAcceptFilter implements Filter<Path> {
    @Override
    public boolean accept(Path entry){
        return true;
    }

}
