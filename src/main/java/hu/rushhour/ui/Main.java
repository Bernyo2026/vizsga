package hu.rushhour.ui;

import hu.rushhour.model.Board;
import hu.rushhour.model.Vehicle;
import hu.rushhour.service.GameService;

import java.util.Scanner;

/**
 * A konzolos felület és a fő játékhurok (Game Loop).
 */
public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        GameService gameService = new GameService(board);
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Kétjátékos Rush Hour (Csúcsforgalom) ===");
        System.out.println("Te vagy az 'A' autó -> Cél: eljutni a jobb szélre.");
        System.out.println("A gép a 'B' autó -> Cél: eljutni a bal szélre.");
        System.out.println("Irányítás: add meg a jármű nevét és a lépések számát (pl: A 2 vagy C -1). Kilépés: q");

        boolean running = true;
        while (running) {
            printGrid(board);

            // Játékos köre
            System.out.print("Te jössz! Lépésed (pl. A 2): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length == 2) {
                try {
                    String name = parts[0];
                    int steps = Integer.parseInt(parts[1]);

                    boolean success = gameService.moveVehicle(name, steps);
                    if (!success) continue; // Ha sikertelen volt, újra te jössz

                } catch (NumberFormatException e) {
                    System.out.println("Hiba: A lépésnek számnak kell lennie!");
                    continue;
                }
            } else {
                System.out.println("Hibás formátum! Példa: A 2");
                continue;
            }

            // Ellenőrzés a te lépésed után
            if (gameService.checkWinner().equals("PLAYER")) {
                printGrid(board);
                System.out.println("Gratulálok, nyertél! 🎉");
                break;
            }

            // Gép köre
            gameService.computerTurn();

            // Ellenőrzés a gép lépése után
            if (gameService.checkWinner().equals("COMPUTER")) {
                printGrid(board);
                System.out.println("Sajnálom, a gép győzött! 🤖");
                break;
            }
        }

        System.out.println("Vége a játéknak.");
    }

    private static void printGrid(Board board) {
        char[][] grid = new char[board.getHeight()][board.getWidth()];
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                grid[i][j] = '.';
            }
        }

        for (Vehicle v : board.getVehicles()) {
            for (int i = 0; i < v.getLength(); i++) {
                if (v.isHorizontal()) {
                    grid[v.getY()][v.getX() + i] = v.getName().charAt(0);
                } else {
                    grid[v.getY() + i][v.getX()] = v.getName().charAt(0);
                }
            }
        }

        System.out.println("\n    0 1 2 3 4 5 6 7 8 9 10 11");
        for (int i = 0; i < board.getHeight(); i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < board.getWidth(); j++) {
                System.out.print(grid[i][j] + " ");
            }
            if (i == 2) System.out.print(" <-- Te célod (A)");
            if (i == 3) System.out.print(" <-- Gép célja (B)");
            System.out.println();
        }
        System.out.println();
    }
}