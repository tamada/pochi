package jp.cafebabe.kunai.source;

import org.junit.Test;

import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AbstractDataSourceTest{
    @Test
    public void testBasic() throws Exception{
        try(PlainFileDataSource source = new PlainFileDataSource(Paths.get("target/test-classes/dummy.class/emptyfile"))){
            assertThat(source.getStartIndex("/hoge.class"), is(1));
            assertThat(source.getStartIndex("hoge.class"), is(0));
            assertThat(source.trimName("/hoge.class", 1, 5), is("hoge"));
            assertThat(source.trimName("aaa", -1, 1), is("aaa"));
            assertThat(source.trimName("aaa", 2, 1), is("aaa"));
            assertThat(source.trimName("aaa", 0, 1), is("a"));
        }
    }

}
