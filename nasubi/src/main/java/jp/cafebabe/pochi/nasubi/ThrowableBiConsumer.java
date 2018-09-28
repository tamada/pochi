package jp.cafebabe.pochi.nasubi;

public interface ThrowableBiConsumer<S1, S2, E extends Exception> {
    void accept(S1 s1, S2 s2) throws E;
}
