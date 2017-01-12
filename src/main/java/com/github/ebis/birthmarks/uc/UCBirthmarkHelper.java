package com.github.ebis.birthmarks.uc;

import java.util.Arrays;
import java.util.Optional;

import org.objectweb.asm.Type;

import com.github.ebis.birthmarks.entities.Elements;
import com.github.ebis.config.Configuration;

public class UCBirthmarkHelper {
    private Names names = new Names();
    private Configuration context;

    public UCBirthmarkHelper(Configuration context){
        this.context = context;
    }

    public Elements build(){
        return names.build();
    }

    private String normalize(String name){
        if(name.startsWith("L") && name.endsWith(";"))
            name = name.substring(1, name.length() - 1);
        return name.replace('/', '.');
    }

    public void addAll(String[] names){
        if(names == null) return;
        Arrays.stream(names)
        .forEach(name -> add(name));
    }

    public void add(String name){
        name = name.replace('/', '.');
        if(!names.contains(name) && context.isSystemName(name))
            names.add(normalize(name));
    }

    public void add(Optional<String> name){
        name.ifPresent(item -> add(item));
    }

    public void add(Type type){
        type = stripType(type);
        if(checkType(type))
            add(parse(type));
    }

    private Type stripType(Type type){
        while(type.getSort() == Type.ARRAY)
            type = type.getElementType();
        return type;
    }

    private Optional<String> parse(Type type){
        if(type.getSort() == Type.OBJECT)
            return Optional.of(normalize(type.getClassName()));
        return Optional.empty();
    }

    public boolean checkType(Type type){
        return type.getSort() == Type.OBJECT &&
                context.isSystemName(
                        type.getClassName());
    }
}
