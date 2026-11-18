package hu.rushhour.model;

import java.util.Objects;

/**
 * Egy járművet reprezentáló osztály (VO - Value Object).
 */
public class Vehicle {
    private String name;          // Jármű neve (pl. "A" a te autód, "B" a gépé, "C", "D" akadályok)
    private int x;                // X koordináta (oszlop: 0-11)
    private int y;                // Y koordináta (sor: 0-5)
    private int length;           // Hosszúság (2 vagy 3)
    private boolean isHorizontal; // true = vízszintes, false = függőleges

    public Vehicle(String name, int x, int y, int length, boolean isHorizontal) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.length = length;
        this.isHorizontal = isHorizontal;
    }

    public String getName() { return name; }
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getLength() { return length; }
    public boolean isHorizontal() { return isHorizontal; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(name, vehicle.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Vehicle{name='" + name + "', x=" + x + ", y=" + y + "}";
    }
}