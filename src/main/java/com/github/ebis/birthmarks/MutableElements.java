package com.github.ebis.birthmarks;

import java.util.List;
import java.util.stream.Stream;

public class MutableElements<T> extends Elements<T> {
    private static final long serialVersionUID = -8534120646567554373L;

    public MutableElements(){
    }

    public MutableElements(List<Element<T>> list){
        super(list);
    }

    public MutableElements(Stream<Element<T>> array){
        super(array);
    }

    public final void add(Element<T> element) {
        elements.add(element);
    }

    public final void add(T element) {
        add(new Element<T>(element));
    }
}
