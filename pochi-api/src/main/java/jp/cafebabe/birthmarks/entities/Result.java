package jp.cafebabe.birthmarks.entities;

public class Result<T extends Acceptor<T>> {
    private T result;

    public Result(T result){
        this.result = result;
    }

    public void accept(Visitor<T> visitor){
        result.accept(visitor);
    }
}
