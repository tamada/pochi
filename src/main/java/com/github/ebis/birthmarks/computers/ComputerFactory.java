package com.github.ebis.birthmarks.computers;

import java.util.HashMap;
import java.util.Map;

public class ComputerFactory {
    private static final ComputerFactory instance = new ComputerFactory();

    public static ComputerFactory getInstance() {
        return instance;
    }

    private Map<ComputerName, Computer> computers = new HashMap<>();

    public ComputerFactory() {
        computers.put(ComputerName.DiceIndex, new DiceIndexComputer());
        computers.put(ComputerName.SimpsonIndex, new SimpsonIndexComputer());
        computers.put(ComputerName.JaccardIndex, new JaccardIndexComputer());
    }

    public Computer get(ComputerName type) {
        return computers.get(type);
    }

    public void register(ComputerName type, Computer computer){
        computers.put(type, computer);
    }
}
