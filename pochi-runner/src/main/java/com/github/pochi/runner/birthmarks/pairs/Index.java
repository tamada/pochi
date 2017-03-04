package com.github.pochi.runner.birthmarks.pairs;

class Index{
    private int current;

    public Index(int index){
        this.current = index;
    }

    public int index(){
        int i = current;
        current++;
        return i;
    }
}
