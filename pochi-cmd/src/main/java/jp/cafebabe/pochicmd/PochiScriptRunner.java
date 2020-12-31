package jp.cafebabe.pochicmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PochiScriptRunner extends AbstractRunner {
    @Override
    public List<String> constructCommands(String prog, Arguments args) {
        List<String> commands = super.constructCommands(prog, args);
        args.arguments().forEach(commands::add);
        return commands;
    }

    @Override
    protected void appendBaseScript(List<String> list, Arguments args) {
        list.add("--basescript");
        list.add("PochiBase");
    }

    @Override
    public String targetName() {
        return "groovy";
    }

    @Override
    public Mode mode() {
        return Mode.ScriptFile;
    }
}
