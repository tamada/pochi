package jp.cafebabe.pochi.birthmarks.entities;

public interface Acceptor<T> {
    void accept(Visitor visitor);

}
