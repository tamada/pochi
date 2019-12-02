package jp.cafebabe.pochi.cli;

import static org.junit.Assert.assertTrue;

public class Assert {
    public static void assertThrows(Class<? extends Exception> exceptionClass, ThrowableConsumer<Exception> consumer){
        boolean thrown = false;
        try {
            consumer.consume();
        } catch (Exception ex) {
            assertTrue(exceptionClass.isInstance(ex));
            thrown = true;
        }
        assertTrue(thrown);
    }
}
