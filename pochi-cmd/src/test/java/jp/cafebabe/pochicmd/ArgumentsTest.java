package jp.cafebabe.pochicmd;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class ArgumentsTest{
    @Test
    public void testParseArgsScriptFiles() {
        String[] giveArgs = {"some/script/file", "args1", "args2"};
        Arguments args = Arguments.parse(giveArgs);

        assertThat(args.isVerbose(), is(false));
        assertThat(args.isHelp(), is(false));
        assertThat(args.expression(), is(nullValue()));
        assertThat(args.findMode(), is(Runner.Mode.ScriptFile));
        assertThat(args.classpaths().count(), is(0L));
        assertThat(args.arguments().count(), is(3L));
    }

    @Test
    public void testParseArgsOneLiner() {
        String[] giveArgs = {"-e", "some expression"};
        Arguments args = Arguments.parse(giveArgs);

        assertThat(args.isVerbose(), is(false));
        assertThat(args.isHelp(), is(false));
        assertThat(args.expression(), is("some expression"));
        assertThat(args.findMode(), is(Runner.Mode.OneLineExpression));
        assertThat(args.classpaths().count(), is(0L));
        assertThat(args.arguments().count(), is(0L));
    }

    @Test
    public void testParseArgs() {
        String[] giveArgs = {"--verbose"};
        Arguments args = Arguments.parse(giveArgs);

        assertThat(args.isVerbose(), is(true));
        assertThat(args.isHelp(), is(false));
        assertThat(args.expression(), is(nullValue()));
        assertThat(args.findMode(), is(Runner.Mode.Interactive));
        assertThat(args.classpaths().count(), is(0L));
        assertThat(args.arguments().count(), is(0L));
    }

    @Test
    public void testParseArgsHelp() {
        String[] giveArgs = {"-v", "--help"};
        Arguments args = Arguments.parse(giveArgs);

        assertThat(args.isVerbose(), is(true));
        assertThat(args.isHelp(), is(true));
        assertThat(args.expression(), is(nullValue()));
        assertThat(args.findMode(), is(Runner.Mode.HelpMode));
        assertThat(args.classpaths().count(), is(0L));
        assertThat(args.arguments().count(), is(0L));
    }
}
