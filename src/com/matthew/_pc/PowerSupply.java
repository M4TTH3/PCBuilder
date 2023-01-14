package com.matthew._pc;

public class PowerSupply extends ComputerPart {

    private final int powerRating;

    public PowerSupply(double cost, String name, int powerRating) {
        super(cost, 0, name);
        this.powerRating = powerRating;
    }

    @Override
    public int compareTo(String getWattage) {
        int wattage = Integer.parseInt(getWattage);
        if (this.powerRating >= wattage) {
            return 0;
        } else {
            return -1;
        }
    }

}
