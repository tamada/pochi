package jp.cafebabe.kunai.source;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

public class DirectoryTraverserTest {
    private DirectoryTraverser traverser = new DirectoryTraverser();

    @Test
    public void testBasic() throws Exception{
        List<Path> list = traverser.traverse(Paths.get("target/test-classes/hello/target/classes"));

        assertThat(list.size(), is(2));
    }

    @Test
    public void testNotExistPath() throws Exception{
        List<Path> list = traverser.traverse(Paths.get("not/exist/path"));

        assertThat(list.size(), is(0));
    }

    @Test
    public void testNullValue() throws Exception{
        List<Path> list = traverser.traverse(new Path[] { null });

        assertThat(list.size(), is(0));
    }
}
