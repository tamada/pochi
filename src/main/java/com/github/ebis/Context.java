package com.github.ebis;

import com.github.ebis.birthmarks.BirthmarkService;

public class Context {
    private Rules manager = new Rules();
    private BirthmarkServices services = new BirthmarkServices();

    public Rules rules() {
        return manager;
    }

    public BirthmarkService services(){
        return services;
    }
}
