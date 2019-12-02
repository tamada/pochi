package jp.cafebabe.pochi.nasubi;

@FunctionalInterface
public interface ThrowableBiPredicate<S1, S2, E extends Exception> {
    boolean test(S1 s1, S2 s2) throws E;
}
