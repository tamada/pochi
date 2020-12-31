package jp.cafebabe.pochicmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InteractiveRunner extends AbstractRunner {
    @Override
    public void execute(Arguments args, Environment env) throws IOException {
        List<String> argv = constructCommands("groovysh", args);
        exec(buildProcessBuilder(args).command(argv).start());
    }

    @Override
    protected void appendBaseScript(List<String> list, Arguments args) {
        list.add("-e");
        list.add("pochi = new jp.cafebabe.pochi.BirthmarkSystemHelper()");
    }

    @Override
    public Mode mode() {
        return Mode.Interactive;
    }
}
