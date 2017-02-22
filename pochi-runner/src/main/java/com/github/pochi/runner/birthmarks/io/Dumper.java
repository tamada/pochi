package com.github.pochi.runner.birthmarks.io;

import com.github.pochi.runner.birthmarks.entities.BirthmarkType;
import com.github.pochi.runner.birthmarks.entities.Results;

public interface Dumper<T>{
    public abstract void print(Results<T> results);

    public abstract void print(BirthmarkType type, T target);
}
