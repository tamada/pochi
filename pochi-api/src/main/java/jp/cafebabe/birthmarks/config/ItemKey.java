package jp.cafebabe.birthmarks.config;

public class ItemKey extends Value{
    private static final long serialVersionUID = 482687174682472803L;

    public ItemKey(String key){
        super(key);
    }

    public static ItemKey of(String key) {
        return new ItemKey(key);
    }
}
