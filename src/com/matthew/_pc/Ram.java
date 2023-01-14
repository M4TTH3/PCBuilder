package com.matthew._pc;

public class Ram extends ComputerPart{

    private final int DDRGen;
    private final int cl; // Example cl19. It represents the RAM's timings.
    private final int clockSpeedMHZ;

    public Ram(double cost, int wattage, String name, int DDRGen, int cl, int clockSpeedMHZ) {
        super(cost, wattage, name);
        this.DDRGen = DDRGen;
        this.cl = cl;
        this.clockSpeedMHZ = clockSpeedMHZ;
    }

    static final CompareParts<Ram, Motherboard> COMPARE_PARTS = new CompareParts<Ram, Motherboard>() {
        @Override
        public int compare(Ram ram, Motherboard motherboard) {
            if (ram.DDRGen == motherboard.getDdrGen()) {
                return 0;
            } else {
                return -1;
            }
        }
    };

    @Override
    public int compareTo(String DDRGen) {
        int gen = Integer.parseInt(DDRGen);
        if (this.DDRGen == gen) {
            return 0;
        } else {
            return -1;
        }
    }

    public int getCl() {
        return cl;
    }

    public int getClockSpeedMHZ() {
        return clockSpeedMHZ;
    }

    public int getDDRGen() {
        return DDRGen;
    }
}
