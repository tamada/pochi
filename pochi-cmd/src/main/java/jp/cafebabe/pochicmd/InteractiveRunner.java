package jp.cafebabe.pochicmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InteractiveRunner extends AbstractRunner {
    @Override
    public void execute(Arguments args, Environment env) throws IOException {
        List<String> argv = new ArrayList<>();
        argv.addAll(List.of("groovysh", "-classpath", env.classpath(args), "-e", "pochi = new jp.cafebabe.pochi.BirthmarkSystemHelper();"));
        if(args.isVerbose()) {
            argv.add("--debug");
        }
        exec(buildProcessBuilder(args).command(argv).start());
    }

    @Override
    public Mode mode() {
        return Mode.Interactive;
    }
}
