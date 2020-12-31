package jp.cafebabe.pochicmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractRunner implements Runner {
    private Environment env = new Environment();

    public final void run(Arguments args) throws IOException {
        execute(args, env);
    }

    public List<String> constructCommands(String prog, Arguments args) {
        List<String> commands = new ArrayList<>();
        commands.add(prog);
        appendClasspath(commands, args);
        appendBaseScript(commands, args);
        if(args.isVerbose()) {
            commands.add("--debug");
        }
        return commands;
    }

    protected void appendBaseScript(List<String> list, Arguments args){
    }

    public void appendClasspath(List<String> commands, Arguments args) {
        commands.add("-classpath");
        commands.add(env.classpath(args));
    }

    public abstract String targetName();

    public void execute(Arguments args, Environment env) throws IOException{
        List<String> argv = constructCommands(targetName(), args);
        exec(buildProcessBuilder(args).command(argv).start());
    }

    public ProcessBuilder buildProcessBuilder(Arguments args) {
        ProcessBuilder builder = new ProcessBuilder()
                .inheritIO();
        builder = args.setupProcessBuilder(builder);
        return builder;
    }

    protected void exec(Process process) {
        try {
            process.waitFor();
        } catch(InterruptedException e) {
        }
        process.destroy();
    }
}
