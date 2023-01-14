package com.matthew._pc;

public class GraphicsCard extends ComputerPart{

    private final int pciGenBandwidth; // Holds value of pci generation bandwidth. Ex. 5500xt on pcie 4

    static final CompareParts<GraphicsCard, Motherboard> COMPARE_PARTS = new CompareParts<GraphicsCard, Motherboard>() {
        @Override
        public int compare(GraphicsCard graphicsCard, Motherboard motherboard) {
            if (graphicsCard.pciGenBandwidth == motherboard.getPciGen()) {
                return 0;
            } else {
                return -1;
            }
        }
    };

    public GraphicsCard(double cost, int wattage, String name, int pciGenBandwidth) {
        super(cost, wattage, name);
        this.pciGenBandwidth = pciGenBandwidth;
    }

    @Override
    public int compareTo(String pci) {
        int gen = Integer.parseInt(pci);
        if (this.pciGenBandwidth <= gen) {
            return 0;
        } else {
            return -1;
        }
    }
}
