package com.matthew._pc;

public class CPU extends ComputerPart {

    // Brand can be AMD or Intel
    private final String[] chipsetRequired;
    private final int cores;
    private final int threads;

    public CPU(double cost, int wattage, String name, int cores, int threads, String[] chipsetRequired) {
        super(cost, wattage, name);
        this.cores = cores;
        this.threads = threads;
        this.chipsetRequired = chipsetRequired;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    public String[] getChipsetRequired() {
        return chipsetRequired;
    }

    public int getCores() {
        return cores;
    }

    public int getThreads() {
        return threads;
    }
}
