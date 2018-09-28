package jp.cafebabe.pochi.kunai.sink;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Test;

import jp.cafebabe.pochi.kunai.util.PathHelper;

public class DirectoryMakerTest {
    @Test
    public void testBasic(){
        FileSystem system = FileSystems.getDefault();
        DirectoryMaker.mkdirs(Paths.get("a/b/c"), system);

        assertThat(Files.isDirectory(Paths.get("a/b/c")), is(true));
        assertThat(Files.exists(Paths.get("a/b/c/d")), is(false));
        
        DirectoryMaker.mkdirs(Paths.get("a/b/c/d/e"), system);
        
        assertThat(Files.isDirectory(Paths.get("a/b/c/d/e")), is(true));
    }

    @After
    public void tearDown(){
        PathHelper.deleteAll(Paths.get("a"));
    }
}
