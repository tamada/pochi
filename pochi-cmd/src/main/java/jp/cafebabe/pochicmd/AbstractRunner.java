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
        if(args.configFile().isPresent()) {
            builder.environment().put(Main.CONFIG_NAME, args.configFile().get());
        }
        if(args.workingDir().isPresent()){
            builder = builder.directory(new File(args.workingDir().get()));
        }
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

    private void pipeInThread(InputStream in, OutputStream out, ExecutorService executor) {
        executor.submit(() -> {
            try(in; out) {
                pipe(in, out);
            } catch(IOException e) {
                e.printStackTrace();
            }
            System.out.println("pipe closed");
        });
    }

    private void pipe(InputStream in, OutputStream out) throws IOException {
        int data;
        while((data = in.read()) != -1) {
            out.write(data);
        }
    }
}
