package jp.cafebabe.pochi.birthmarks.config;

public class ItemValue extends Value{
    private static final long serialVersionUID = 9220874396687981269L;

    public ItemValue(String item){
        super(item);
    }

    public static final ItemValue of(String item){
        return new ItemValue(item);
    }
}
