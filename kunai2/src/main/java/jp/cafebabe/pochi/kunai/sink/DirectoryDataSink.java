package jp.cafebabe.pochi.kunai.sink;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jp.cafebabe.pochi.kunai.entries.Entry;
import jp.cafebabe.pochi.kunai.entries.KunaiException;

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
        try(OutputStream out = Files.newOutputStream(path)){
            DataSinkHelper.copy(in, out);
        } catch(IOException e){
            throw new KunaiException(e.getMessage());
        }
    }

    private Path createPath(Entry entry){
        if(entry.isClass())
            return Paths.get(entry.className().toString().replace('.', '/') + ".class");
        return Paths.get(entry.path().toString());
    }
}
