package hu.rushhour.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A játéktábla, amely tárolja a járműveket (Collection API: List).
 */
public class Board {
    private static final int WIDTH = 12; // 12 oszlop
    private static final int HEIGHT = 6;  // 6 sor
    private List<Vehicle> vehicles;

    public Board() {
        vehicles = new ArrayList<>();
        initBoard();
    }

    private void initBoard() {
        // "A" -> Te autód (jobb szélre akar kijutni a 2. sorban)
        vehicles.add(new Vehicle("A", 1, 2, 2, true));
        
        // "B" -> Gép autója (bal szélre akar kijutni a 3. sorban)
        vehicles.add(new Vehicle("B", 9, 3, 2, true));

        // Semleges akadályok a pályán
        vehicles.add(new Vehicle("C", 4, 0, 3, true));
        vehicles.add(new Vehicle("D", 0, 1, 2, false));
        vehicles.add(new Vehicle("E", 6, 2, 3, false));
    }

    public List<Vehicle> getVehicles() { return vehicles; }
    public int getWidth() { return WIDTH; }
    public int getHeight() { return HEIGHT; }

    public Vehicle findVehicleByName(String name) {
        for (Vehicle v : vehicles) {
            if (v.getName().equalsIgnoreCase(name)) {
                return v;
            }
        }
        return null;
    }
}