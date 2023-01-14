package com.matthew._pc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final List<CPU> cpus = List.of(
            new CPU(249.00, 65, "AMD R5 3600", 6, 12,
                    new String[]{"x570", "b450", "x470"}),
            new CPU(279, 95, "Intel i5 9600k", 6, 6,
                    new String[]{"z370, z390"}),
            new CPU(649, 105, "AMD R9 3900x", 12, 24,
                    new String[]{"x570", "x470"}),
            new CPU(699, 95, "Intel i9 9900k", 8, 16,
                    new String[]{"z390"}),
            new CPU(474, 105, "AMD R7 3800x", 8, 16,
                    new String[]{"b450", "x470", "x570"})
    );

    public static final List<Motherboard> motherboards = List.of(
            new Motherboard(154.99, 70, "B450 MSI Tomahawk Max", "B450", 4, 3),
            new Motherboard(258.50, 70, "x570 ASUS TUF Gaming", "x570", 4, 4),
            new Motherboard(305.75, 30, "x570 Gigabyte Aorus pro wifi", "x570", 4,
                    4),
            new Motherboard(164.99, 70, "z390 MSI A-Pro", "z390", 4,3),
            new Motherboard(248.88, 70, "x470 ASUS Rog Strix", "x470", 4, 3),
            new Motherboard(191.50, 70, "z370 ASUS Prime-A II", "z370", 4, 3)
    );

    private static final List<Ram> rams = List.of(
            new Ram(104.99, 14, "Corsair Vengeance", 4, 15, 3000),
            new Ram(135.99, 14, "G.Skill Trident Z", 4, 18, 3600),
            new Ram(121.99, 14, "Corsair Vengeance Pro", 4, 16, 3200)
    );

    private static final List<GraphicsCard> graphicsCards = List.of(
            new GraphicsCard(549.99, 205, "Rx 5700XT", 4),
            new GraphicsCard(529.99, 165, "RTX 2060 Super", 3),
            new GraphicsCard(369.99, 135, "RX 5600XT", 4),
            new GraphicsCard(429.99, 125, "GTX 1660 Super", 3)
    );

    private static final List<PowerSupply> powerSupplies = List.of(
            new PowerSupply(129.99, "Corsair RM", 750),
            new PowerSupply(59.99, "EVGA BR", 500)
    );


    public static void main(String[] args) {
        ArrayList<PC> PCs = new ArrayList<>();
        CPU cpu = getCPU();
        for (int i = 0; i < 3; i++) {
            PCs.add(buildPC(cpu));
        }
        System.out.println("Built 3 different PCs with the " + cpu.getName());

        boolean flag = true;
        while (flag) {
            System.out.println("Pick PC 1 - " + PCs.size());
            PC tempPC;
            if (scanner.hasNextInt()) {
                int index = scanner.nextInt() - 1;
                scanner.nextLine();
                tempPC = PCs.get(index);
            } else {
                System.out.println("Invalid number");
                scanner.nextLine();
                continue;
            }

            boolean sameBoard = true;
            while (sameBoard) {
                instructions();
                String input = scanner.nextLine();
                System.out.println();
                switch (input.toLowerCase()) {
                    case "q":
                        flag = false;
                        break;
                    case "new":
                        sameBoard = false;
                        break;
                    case "add":
                        PCs.add(buildPC(cpu));
                        sameBoard = false;
                        break;
                    case "cost":
                        System.out.println(tempPC.getTotalCost());
                        break;
                    case "parts":
                        tempPC.getParts();
                        break;
                }
            }
        }
        scanner.close();
    }

    private static void instructions() {
        System.out.println();
        System.out.println("Type in a command:");
        System.out.println("Q - Quit");
        System.out.println("New - Pick another PC");
        System.out.println("Add - Build new PC with original CPU");
        System.out.println("Parts - get the parts of the PC");
        System.out.println("Cost - View the cost of the PC");
    }

    private static CPU getCPU() {
        System.out.println("For these CPUs: ");
        while (true) {
            for(CPU cpu: cpus) {
                System.out.println(cpu.getName());
            }
            System.out.println("Pick one:");
            String cpu = scanner.nextLine();
            for (CPU tempCPU: cpus) {
                if(tempCPU.getName().toLowerCase().equals(cpu.toLowerCase())) {
                    return tempCPU;
                }
            }
            System.out.println("Invalid CPU");
        }
    }

    private static Motherboard getMotherboard(CPU cpu) {
        // Verify there is a compatible motherboard to the cpu
        for (String chipset: cpu.getChipsetRequired()) {
            if (!verify(chipset, Main.motherboards)) {
                return null;
            }
        }
        return getRanPart(Main.motherboards, cpu, Motherboard.COMPARE_PARTS);
    }


    private static Ram getRam(Motherboard motherboard) {
        // Verify there is a compatible Ram.
        String ddrGen = "" + motherboard.getDdrGen();
        if (!verify(ddrGen, Main.rams))
            return null;
        return getRanPart(Main.rams, motherboard, Ram.COMPARE_PARTS);
    }

    public static GraphicsCard getGraphicsCard(Motherboard motherboard, List<GraphicsCard> graphicsCards) {
        // Verify there is a compatible graphics card
        String pciGen = "" + motherboard.getPciGen();
        if (!verify(pciGen, graphicsCards))
            return null;

        return getRanPart(Main.graphicsCards, motherboard, GraphicsCard.COMPARE_PARTS);
    }

    public static PowerSupply getPowerSupply(PC pc, List<PowerSupply> powerSupplies) {
        // Verify compatible power supply
        String wattage = "" + pc.getWattage();
        if (!verify(wattage, powerSupplies))
            return null;
        return getRandomPart(wattage, powerSupplies);
    }

    public static <T extends ComputerPart> T getRandomPart(String comparable, List<T> list) {
        while (true) {
            T temp = ComputerPart.getRandomPart(list);
            if (temp.compareTo(comparable) == 0) {
                return temp;
            }
        }
    }

    public static <T extends ComputerPart, Z> T getRanPart(List<T> parts, Z nextPart, CompareParts<T, Z> comparable) {
        while (true) {
            T temp = ComputerPart.getRandomPart(parts);
            if (comparable.compare(temp, nextPart) == 0) {
                return temp;
            }
        }
    }

    public static <T extends ComputerPart> boolean verify(String comparable, List<T> arrayList) {
        // Verify compatibility
        for (T temp: arrayList) {
            if (temp.compareTo(comparable) == 0) {
                return true;
            }
        }
        return false;
    }

    public static PC buildPC(CPU cpu) {
        Motherboard motherboard = getMotherboard(cpu);
        Ram ram = getRam(motherboard);
        GraphicsCard graphicsCard = getGraphicsCard(motherboard, graphicsCards);
        PowerSupply powerSupply = getPowerSupply(new PC(cpu, motherboard, ram, graphicsCard, null), powerSupplies);
        return new PC(cpu, motherboard, ram, graphicsCard, powerSupply);
    }

}
