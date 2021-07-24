package jp.cafebabe.pochi.birthmarks.uc;

import java.util.Arrays;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Elements;
import org.objectweb.asm.Type;

public class UCBirthmarkHelper {
    private Names names = new Names();
    private Configuration context;

    public UCBirthmarkHelper(Configuration context){
        this.context = context;
    }

    public Elements<String> build(){
        return names.build();
    }

    public void addAll(String[] names){
        if(names != null)
            Arrays.stream(names)
            .forEach(this::add);
    }

    public void addAll(Type[] types){
        if(types != null)
            Arrays.stream(types)
            .forEach(this::add);
    }

    public void add(String name){
        String normalizedName = normalize(name);
        if(!names.contains(normalizedName) && context.match(normalizedName))
            names.add(normalizedName);
    }

    public void add(Type type){
        Type strippedType = stripType(type);
        if(strippedType.getSort() == Type.OBJECT)
            add(strippedType.getClassName());
    }

    String normalize(String givenName){
        String name = givenName;
        if(name.startsWith("L") && name.endsWith(";"))
            name = name.substring(1, name.length() - 1);
        return name.replace('/', '.');
    }

    Type stripType(Type givenType){
        Type type = givenType;
        while(type.getSort() == Type.ARRAY)
            type = type.getElementType();
        return type;
    }
}
