package jp.cafebabe.kunai.sink;

import java.nio.file.Path;

public class WarFileDataSink extends JarFileDataSink {
    public WarFileDataSink(Path path){
        super(DataSinkHelper.buildFileSystem(path), "/WEB-INF/classes");
    }
}
