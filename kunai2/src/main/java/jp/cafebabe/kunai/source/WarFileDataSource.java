package jp.cafebabe.kunai.source;

import java.nio.file.FileSystem;
import java.nio.file.Path;

public class WarFileDataSource extends JarFileDataSource{
    private static final String WAR_CLASSES_PREFIX = "/WEB-INF/classes/";

    public WarFileDataSource(Path base, FileSystem system){
        super(base, system);
    }

    @Override
    protected int getStartIndex(String name){
        if(name.startsWith(WAR_CLASSES_PREFIX))
            return WAR_CLASSES_PREFIX.length();
        return 0;
    }
}
