package com.github.ebis.birthmarks.computers;

import com.github.kunai.entries.Name;

public class ComputerName extends Name{
    public static final ComputerName JaccardIndex = new ComputerName("JaccardIndex");
    public static final ComputerName SimpsonIndex = new ComputerName("SimpsonIndex");
    public static final ComputerName DiceIndex = new ComputerName("DiceIndex");

    public ComputerName(String name){
        super(name);
    }
}
