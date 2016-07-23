package com.github.ebis.birthmarks;

public class BirthmarkException extends Exception {
    private static final long serialVersionUID = 5768214410885576908L;

    public BirthmarkException(Exception e) {
        super(e);
    }

    public BirthmarkException(String message) {
        super(message);
    }
}
