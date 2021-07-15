package jp.cafebabe.pochicmd;

import io.vavr.control.Try;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InteractiveRunner implements Runner {
    private static final String INTERACTIVE_SHELL_NAME = "groovysh";
    private Environment env = new Environment();

    @Override
    public final void run(Arguments args) throws IOException {
        List<String> argv = constructCommands(INTERACTIVE_SHELL_NAME, args);
        perform(buildProcessBuilder(args).command(argv).start());
    }

    public List<String> constructCommands(String prog, Arguments args) {
        List<String> commands = new ArrayList<>();
        commands.add(prog);
        appendClasspath(commands, args);
        appendBaseScript(commands);
        if(args.isVerbose()) {
            commands.add("--debug");
        }
        return commands;
    }

    public void appendClasspath(List<String> commands, Arguments args) {
        commands.add("-classpath");
        commands.add(env.classpath(args));
    }

    public ProcessBuilder buildProcessBuilder(Arguments args) {
        ProcessBuilder builder = new ProcessBuilder()
                .inheritIO();
        builder = args.setupProcessBuilder(builder);
        return builder;
    }

    private void perform(Process process) {
        Try<Integer> groovysh = Try.of(() -> process.waitFor())
                .andThen(() -> process.destroy());
        groovysh.onFailure(e -> System.out.println("Interactive mode of pochi requires the groovy installation to the local environment."));
    }

    private void appendBaseScript(List<String> list) {
        list.add("-e");
        list.add("pochi = new jp.cafebabe.pochi.BirthmarkSystemHelper()");
    }

    @Override
    public Mode mode() {
        return Mode.Interactive;
    }
}
