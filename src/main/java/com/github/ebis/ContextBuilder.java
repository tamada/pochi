package com.github.ebis;

import java.io.IOException;
import java.io.InputStream;

public class ContextBuilder {
    private static final Context DEFAULT = new Context();

    public Context build(InputStream in) throws IOException {
        return null;
    }

    public static Context getDefault(){
        return DEFAULT;
    }
}
