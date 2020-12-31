package jp.cafebabe.pochicmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExpressionRunner extends AbstractRunner {
    @Override
    public void execute(Arguments args, Environment env) throws IOException {
        List<String> argv = constructCommands("groovy", args);
        exec(buildProcessBuilder(args).command(argv).start());
    }

    @Override
    protected void appendBaseScript(List<String> commands, Arguments args) {
        commands.add("-b");
        commands.add("PochiBase");
        commands.add("-e");
        commands.add(args.expression());
    }

    @Override
    public Mode mode() {
        return Mode.OneLineExpression;
    }
}
