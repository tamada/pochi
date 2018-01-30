package com.github.pochi.runner.scripts.helper;

public class SystemInfoHelper {
    public String version(){
        Package p = Package.getPackage("com.github.pochi.runner.scripts.helper");
        return p.getImplementationVersion();
    }

    public double measure(Runnable action) {
        long startTime = System.nanoTime();
        action.run();
        long endTime = System.nanoTime();

        return endTime - startTime;
    }
}
