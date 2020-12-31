package jp.cafebabe.pochicmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PochiScriptRunner extends AbstractRunner {
    @Override
    public void execute(Arguments args, Environment env) throws IOException {
        List<String> argv = constructCommands("groovy", args);
        args.arguments().forEach(argv::add);
        exec(buildProcessBuilder(args).command(argv).start());
    }

    @Override
    protected void appendBaseScript(List<String> list, Arguments args) {
        list.add("--basescript");
        list.add("PochiBase");
    }

    @Override
    public Mode mode() {
        return Mode.ScriptFile;
    }
}
