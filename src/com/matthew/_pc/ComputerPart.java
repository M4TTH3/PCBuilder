package com.matthew._pc;

import java.util.List;
import java.util.Random;

public abstract class ComputerPart implements Comparable<String> {

    private final double cost;
    private final int wattage;

    private final String name;

    public ComputerPart(double cost, int wattage, String name) {
        this.cost = cost;
        this.wattage = wattage;
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public int getWattage() {
        return wattage;
    }

    public String getName() {
        return name;
    }

    public static double cost(ComputerPart[] computerParts) {
        double cost = 0;
        for (ComputerPart tempParts : computerParts) {
            if (tempParts != null)
                cost += tempParts.getCost();
        }
        return cost;
    }

    public static int wattage(ComputerPart[] computerParts) {
        int watts = 0;
        for (ComputerPart tempParts : computerParts) {
            if (tempParts != null)
                watts += tempParts.getWattage();
        }
        return watts;
    }

    public static <T extends ComputerPart> T getRandomPart(List<T> parts) {
        Random random = new Random();
        int index = random.nextInt(parts.size());
        return parts.get(index);
    }
}
