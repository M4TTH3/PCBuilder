package com.matthew._pc;

public class PC {

    private final CPU cpu;
    private final Motherboard motherboard;
    private final Ram ram;
    private final GraphicsCard gpu;
    private final PowerSupply psu;

    private final double totalCost;
    private final int wattage;

    public PC(CPU cpu, Motherboard motherboard, Ram ram, GraphicsCard gpu, PowerSupply psu) {
        this.cpu = cpu;
        this.motherboard = motherboard;
        this.ram = ram;
        this.gpu = gpu;
        this.psu = psu;

        totalCost = ComputerPart.cost(new ComputerPart[]{cpu, motherboard, ram, gpu, psu});
        wattage = ComputerPart.wattage(new ComputerPart[]{cpu, motherboard, ram, gpu});
    }

    public void getParts() {
        System.out.println(cpu.getName() + " - " + cpu.getCost());
        System.out.println(motherboard.getName() + " - " + motherboard.getCost());
        System.out.println(ram.getName() + " - " + ram.getCost());
        System.out.println(gpu.getName() + " - " + gpu.getCost());
        System.out.println(psu.getName() + " - " + psu.getCost());
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getWattage() {
        return wattage;
    }

    @Override
    public String toString() {
        return "PC: ";
    }
}
