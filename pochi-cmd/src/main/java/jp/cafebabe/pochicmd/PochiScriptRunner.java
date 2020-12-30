package jp.cafebabe.pochicmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PochiScriptRunner extends AbstractRunner {
    @Override
    public void execute(Arguments args, Environment env) throws IOException {
        List<String> argv = new ArrayList<>();
        argv.addAll(List.of("groovy", "-classpath", env.classpath(args), "--basescript", "PochiBase"));
        if(args.isVerbose()) {
            argv.add("--debug");
        }
        args.arguments().forEach(argv::add);
        exec(buildProcessBuilder(args).command(argv).start());
    }

    @Override
    public Mode mode() {
        return Mode.ScriptFile;
    }
}
