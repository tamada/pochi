package jp.cafebabe.pochicmd;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;

public abstract class AbstractRunner implements Runner {
    private Environment env = new Environment();

    public final void run(Arguments args) throws IOException {
        execute(args, env);
    }

    public abstract void execute(Arguments args, Environment env) throws IOException;

    public ProcessBuilder buildProcessBuilder(Arguments args) {
        ProcessBuilder builder = new ProcessBuilder()
                .inheritIO();
        builder = args.setupProcessBuilder(builder);
        return builder;
    }

    protected void exec(Process process) {
        while(process.isAlive()){
            try {
                process.waitFor();
            } catch(InterruptedException e) {
            }
        }
        process.destroy();
    }
}
