package com.github.ebis.birthmarks.io;

import com.github.ebis.birthmarks.entities.BirthmarkType;
import com.github.ebis.birthmarks.entities.Results;

public interface Dumper<T>{
    public abstract void print(Results<T> results);

    public abstract void print(BirthmarkType type, T target);
}
