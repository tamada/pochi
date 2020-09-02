package jp.cafebabe.birthmarks.entities;

import java.util.function.Consumer;

public class Progress {
    private int current = 0;
    private long max;
    private Consumer<Percentage> callback;

    public Progress(long max) {
        this(max, percentage -> {});
    }

    public Progress(long max, Consumer<Percentage> callback) {
        this.max = max;
        this.callback = callback;
    }

    public Percentage percent() {
        return new Percentage(current, max);
    }

    public synchronized void update() {
        current++;
        new Thread(createRunnable()).start();
    }

    private Runnable createRunnable() {
        return () -> callback.accept(percent());
    }
}
