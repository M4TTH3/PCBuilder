package com.matthew._pc;

import java.util.Random;

public class Motherboard extends ComputerPart{

    private final String chipset;
    private final int ddrGen; // Get the DDR generation
    private final int pciGen; // Get the pciE generation

    public Motherboard(double cost, int wattage, String name, String chipset, int ddrGen, int pciGen) {
        super(cost, wattage, name);
        this.chipset = chipset;
        this.ddrGen = ddrGen;
        this.pciGen = pciGen;
    }

    static final CompareParts<Motherboard, CPU> COMPARE_PARTS;

    static {
        COMPARE_PARTS = new CompareParts<>() {
            String chip = null;

            @Override
            public int compare(Motherboard motherboard, CPU cpu) {
                // Gets a random chipset from the cpu
                while (true) {
                    Random random = new Random();
                    int index = random.nextInt(cpu.getChipsetRequired().length);
                    if (Main.verify(cpu.getChipsetRequired()[index], Main.motherboards)) {
                        chip = cpu.getChipsetRequired()[index];
                        break;
                    }
                }
                // Compares the two
                if (motherboard.chipset.toLowerCase().equals(chip.toLowerCase())) {
                    return 0;
                } else {
                    return -1;
                }
            }
        };

    }

    @Override
    public int compareTo(String chipset) {
        if (this.chipset.toLowerCase().equals(((String)chipset).toLowerCase())) {
            return 0;
        } else {
            return -1;
        }
    }

    public int getDdrGen() {
        return ddrGen;
    }

    public int getPciGen() {
        return pciGen;
    }

}
