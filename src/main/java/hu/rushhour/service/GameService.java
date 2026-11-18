package hu.rushhour.service;

import hu.rushhour.model.Board;
import hu.rushhour.model.Vehicle;
import java.util.List;
import java.util.Random;

/**
 * A játék logikáját és az ellenfél (Gép AI) lépéseit kezelő osztály.
 */
public class GameService {
    private Board board;
    private Random random;

    public GameService(Board board) {
        this.board = board;
        this.random = new Random();
    }

    public Board getBoard() { return board; }

    /**
     * Jármű mozgatása adott lépésszámmal (lehet + vagy - irányba is).
     */
    public boolean moveVehicle(String vehicleName, int steps) {
        Vehicle vehicle = board.findVehicleByName(vehicleName);
        if (vehicle == null) {
            System.out.println("Hiba: Nincs ilyen nevű jármű!");
            return false;
        }

        if (vehicle.isHorizontal()) {
            int newX = vehicle.getX() + steps;
            if (newX >= 0 && newX + vehicle.getLength() <= board.getWidth()) {
                vehicle.setX(newX);
                return true;
            }
        } else {
            int newY = vehicle.getY() + steps;
            if (newY >= 0 && newY + vehicle.getLength() <= board.getHeight()) {
                vehicle.setY(newY);
                return true;
            }
        }

        System.out.println("Hiba: Ezzel túl messzire mennél (ütközés a falalral)!");
        return false;
    }

    /**
     * A gép (AI) köre: véletlenszerűen választ egy járművet és lép vele.
     */
    public void computerTurn() {
        System.out.println("\n--- Gép köre ---");
        List<Vehicle> vehicles = board.getVehicles();
        boolean moved = false;

        while (!moved) {
            Vehicle randomVehicle = vehicles.get(random.nextInt(vehicles.size()));
            int randomStep = random.nextInt(3) - 1; // -1, 0 vagy 1
            if (randomStep == 0) continue;

            if (randomVehicle.isHorizontal()) {
                int newX = randomVehicle.getX() + randomStep;
                if (newX >= 0 && newX + randomVehicle.getLength() <= board.getWidth()) {
                    randomVehicle.setX(newX);
                    System.out.println("A gép elmozdította a(z) " + randomVehicle.getName() + " járművet.");
                    moved = true;
                }
            } else {
                int newY = randomVehicle.getY() + randomStep;
                if (newY >= 0 && newY + randomVehicle.getLength() <= board.getHeight()) {
                    randomVehicle.setY(newY);
                    System.out.println("A gép elmozdította a(z) " + randomVehicle.getName() + " járművet.");
                    moved = true;
                }
            }
        }
    }

    /**
     * Győzelem ellenőrzése.
     * Visszaadja: "PLAYER", "COMPUTER" vagy "NONE"
     */
    public String checkWinner() {
        Vehicle playerCar = board.findVehicleByName("A");
        Vehicle computerCar = board.findVehicleByName("B");

        // Te nyersz, ha az "A" eléri a jobb szélt
        if (playerCar != null && playerCar.getX() >= board.getWidth() - playerCar.getLength()) {
            return "PLAYER";
        }
        // A gép nyer, ha a "B" eléri a bal szélt (X = 0)
        if (computerCar != null && computerCar.getX() == 0) {
            return "COMPUTER";
        }
        return "NONE";
    }
}