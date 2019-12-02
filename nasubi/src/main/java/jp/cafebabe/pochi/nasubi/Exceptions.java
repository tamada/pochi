package jp.cafebabe.pochi.nasubi;

import java.util.Optional;

public class Exceptions {
    private Exceptions() {
    }

    public static <T> boolean isThrowed(T argument, ThrowableConsumer<T, Exception> consumer) {
        try {
            consumer.accept(argument);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static <T, S> boolean isThrowed(T argument1, S argument2, ThrowableBiConsumer<T, S, Exception> consumer) {
        try {
            consumer.accept(argument1, argument2);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static <T> boolean isThrowedCondition(T argument, ThrowablePredicate<T, Exception> predicate) {
        try {
            return predicate.test(argument);
        } catch (Exception e) {
            return false;
        }
    }

    public static <T, S> boolean isThrowedCondition(T argument1, S argument2,
            ThrowableBiPredicate<T, S, Exception> predicate) {
        try {
            return predicate.test(argument1, argument2);
        } catch (Exception e) {
            return false;
        }
    }

    public static <A, R> Optional<R> map(A argument, ThrowableFunction<A, R, Exception> function) {
        try {
            return Optional.ofNullable(function.apply(argument));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static <A1, A2, R> Optional<R> map(A1 argument1, A2 argument2,
            ThrowableBiFunction<A1, A2, R, Exception> func) {
        try {
            return Optional.ofNullable(func.apply(argument1, argument2));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
