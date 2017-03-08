package com.github.pochi.runner.ui;

import java.io.PrintWriter;

import com.github.pochi.runner.util.Resource;

class InformationPrinter {
    public boolean printIfRequired(boolean version, boolean help, boolean license){
        boolean flag = false;
        flag |= printIfRequired(version,   () -> printFile("/resources/ui/version.txt"));
        flag |= printIfRequired(help,      () -> printFile("/resources/ui/help.txt"));
        flag |= printIfRequired(license,   () -> printFile("/resources/ui/LICENSE"));
        return flag;
    }

    private boolean printIfRequired(boolean flag, Runnable runnable){
        if(flag)
            runnable.run();
        return flag;
    }

    private void printFile(String resourcePath){
        Resource resource = new Resource(resourcePath);
        resource.print(new PrintWriter(System.out));
    }
}
