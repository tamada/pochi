package jp.cafebabe.kunai.sink;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.vavr.control.Try;
import jp.cafebabe.kunai.entries.Entry;
import jp.cafebabe.kunai.entries.KunaiException;

public class DirectoryDataSink extends ClassFileDataSink {

    public DirectoryDataSink(Path path){
        super(path);
    }

    @Override
    public void consume(InputStream in, Entry entry) throws KunaiException {
        Path outputPath = path().resolve(createPath(entry));
        createDirectories(outputPath.getParent());
        consume(in, outputPath);
    }

    private void consume(InputStream in, Path path) throws KunaiException{
        Try.withResources(() -> Files.newOutputStream(path))
                .of(out -> { DataSinkHelper.copy(in, out); return Void.TYPE; })
                .getOrElseThrow(e -> new KunaiException(e.getMessage()));
    }

    private Path createPath(Entry entry){
        if(entry.isClass())
            return Paths.get(toJVMClassName(entry) + ".class");
        return Paths.get(entry.path().toString());
    }
}
