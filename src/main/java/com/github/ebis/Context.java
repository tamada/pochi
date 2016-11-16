package com.github.ebis;

import com.github.ebis.birthmarks.pairmaker.PairMaker;
import com.github.ebis.rules.Rules;

public class Context {
    private Rules manager = new Rules();
    private PairMaker pairs;

    public Rules rules() {
        return manager;
    }
}
