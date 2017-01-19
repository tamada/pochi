package com.github.kunai.sink;

import java.nio.file.Path;

public class WarFileDataSink extends JarFileDataSink {
    public WarFileDataSink(Path path){
        super(DataSinkHelper.buildFileSystem(path), "/WEB-INF/classes");
    }
}
