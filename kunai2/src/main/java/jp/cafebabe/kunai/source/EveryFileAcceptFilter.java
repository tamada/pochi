package jp.cafebabe.kunai.source;

import java.nio.file.DirectoryStream.Filter;
import java.nio.file.Path;

class EveryFileAcceptFilter implements Filter<Path> {
    @Override
    public boolean accept(Path entry){
        return true;
    }

}
