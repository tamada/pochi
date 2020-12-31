package jp.cafebabe.pochicmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExpressionRunner extends AbstractRunner {
    @Override
    protected void appendBaseScript(List<String> commands, Arguments args) {
        commands.add("-b");
        commands.add("PochiBase");
        commands.add("-e");
        commands.add(args.expression());
    }

    @Override
    public String targetName() {
        return "groovy";
    }

    @Override
    public Mode mode() {
        return Mode.OneLineExpression;
    }
}
