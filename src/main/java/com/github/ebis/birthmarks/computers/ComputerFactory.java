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

    }

    public Computer createComputer(ComputerName type) {
        return computers.get(type);
    }
}
