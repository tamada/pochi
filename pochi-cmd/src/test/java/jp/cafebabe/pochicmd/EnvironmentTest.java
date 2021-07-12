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
        System.out.printf("home: %s%n", home);

        assertThat(Files.exists(home.resolve(Path.of("examples"))), is(true));
        assertThat(Files.exists(home.resolve(Path.of("Dockerfile"))), is(true));
    }
}
