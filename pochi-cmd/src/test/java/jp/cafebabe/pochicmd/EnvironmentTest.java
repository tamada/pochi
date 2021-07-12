package jp.cafebabe.pochicmd;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;

public class EnvironmentTest {
    @Test
    public void testPochiHome() {
        Environment env = new Environment();
        Path home = env.pochiHome();
        Path absolute = home.toAbsolutePath();

        Path dockerfile = absolute.resolve(Path.of("Dockerfile"));
        assertThat(String.format("%s: not exist", dockerfile), Files.exists(dockerfile), is(true));

        Path examples = absolute.resolve(Path.of("examples"));
        assertThat(String.format("%s: not exist", examples), Files.isDirectory(examples), is(true));
    }
}
