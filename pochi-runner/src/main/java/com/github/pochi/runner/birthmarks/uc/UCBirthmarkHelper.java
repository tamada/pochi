package com.github.pochi.runner.birthmarks.uc;

import java.util.Arrays;

import org.objectweb.asm.Type;

import com.github.pochi.runner.birthmarks.entities.Elements;
import com.github.pochi.runner.config.Configuration;

public class UCBirthmarkHelper {
    private Names names = new Names();
    private Configuration context;

    public UCBirthmarkHelper(Configuration context){
        this.context = context;
    }

    public Elements build(){
        return names.build();
    }

    public void addAll(String[] names){
        if(names != null)
            Arrays.stream(names).forEach(name -> add(name));
    }

    public void addAll(Type[] types){
        if(types != null)
            Arrays.stream(types).forEach(type -> add(type));
    }

    public void add(String name){
        name = normalize(name);
        if(!names.contains(name) && context.isSystemName(name))
            names.add(name);
    }

    public void add(Type type){
        type = stripType(type);
        if(type.getSort() == Type.OBJECT)
            add(type.getClassName());
    }

    String normalize(String name){
        if(name.startsWith("L") && name.endsWith(";"))
            name = name.substring(1, name.length() - 1);
        return name.replace('/', '.');
    }

    Type stripType(Type type){
        while(type.getSort() == Type.ARRAY)
            type = type.getElementType();
        return type;
    }
}
