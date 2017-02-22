package com.github.pochi.kunai.util;

import static org.junit.Assert.assertTrue;

public class Assert {
    public static <E extends Exception> void assertThrows(Class<E> exceptionClass, ThrowableProcessor<Exception> processor){
        boolean thrown = false;
        try { processor.perform(); }
        catch (Exception ex) {
            thrown = exceptionClass.isInstance(ex);
        }
        assertTrue(thrown);
    }
}
