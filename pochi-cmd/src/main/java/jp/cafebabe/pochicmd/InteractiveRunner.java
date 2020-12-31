package jp.cafebabe.pochicmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InteractiveRunner extends AbstractRunner {
    @Override
    protected void appendBaseScript(List<String> list, Arguments args) {
        list.add("-e");
        list.add("pochi = new jp.cafebabe.pochi.BirthmarkSystemHelper()");
    }

    @Override
    public String targetName() {
        return "groovysh";
    }

    @Override
    public Mode mode() {
        return Mode.Interactive;
    }
}
