package jp.cafebabe.pochicmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExpressionRunner extends AbstractRunner {
    @Override
    public void execute(Arguments args, Environment env) throws IOException {
        List<String> argv = new ArrayList<>();
        argv.addAll(List.of("groovy", "-classpath", env.classpath(args), "-b", "PochiBase", "-e", args.expression()));
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
