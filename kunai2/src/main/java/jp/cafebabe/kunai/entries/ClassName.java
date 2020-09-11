package jp.cafebabe.kunai.entries;

public class ClassName extends Name{
    private static final long serialVersionUID = -1305904437135129418L;

    public ClassName(String name){
        super(name.replace('/', '.'));
    }
}
