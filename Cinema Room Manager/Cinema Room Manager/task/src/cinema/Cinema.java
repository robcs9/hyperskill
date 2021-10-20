package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = in.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = in.nextInt();
        char[][] seatsArr = new char[rows][seats];
        int soldTickets = 0;
        int curRevenue = 0;
        final int totalSeats = rows * seats;
        final int totalRevenue;

        // Initialize filling array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                seatsArr[i][j] = 'S';
            }
        }

        // Calculate total revenue
        if (totalSeats <= 60) {
            totalRevenue = totalSeats * 10;
        } else {
            if (rows % 2 != 0 ) {
                totalRevenue = ((rows / 2) * seats) * 10 + (((rows / 2) * seats) + seats) * 8;
            } else {
                totalRevenue = (totalSeats / 2) * (10 + 8);
            }
        }

        // Start operating
        int opt = 1;
        while (opt != 0) {
            System.out.println("1. Show the seats\n2. Buy a ticket \n3. Statistics \n0. Exit");
            opt = in.nextInt();
            switch (opt) {
                case 1: // Print seats arrangement
                    printSeats(rows, seats, seatsArr);
                    break;
                case 2: // Buy ticket
                    boolean success = false;
                    while (!success) {
                        System.out.println("Enter a row number:");
                        int ticketRow = in.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int ticketSeat = in.nextInt();
                        if (ticketRow < 0 || ticketRow > rows || ticketSeat < 0 || ticketSeat > seats) {
                            System.out.println("Wrong input!");
                        } else if (seatsArr[ticketRow - 1][ticketSeat - 1] == 'B') {
                            System.out.println("That ticket has already been purchased!");
                        } else {
                            seatsArr[ticketRow - 1][ticketSeat - 1] = 'B';// Marking seat
                            int ticketPrice;
                            if (totalSeats <= 60 || ticketRow <= rows / 2) {
                                ticketPrice = 10;
                            } else {
                                ticketPrice = 8;
                            }
                            System.out.printf("Ticket price: $%d\n", ticketPrice);
                            soldTickets++;
                            curRevenue += ticketPrice;
                            success = true;
                        }
                    }
                    break;
                case 3: // Print statistics
                    printStats(soldTickets, totalSeats, curRevenue, totalRevenue);
                    break;
                case 0: // Shutdown
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

    }
    public static void printSeats(int rows, int seats, char[][] seatsArr) {
        System.out.print("Cinema:\n ");
        for (int i = 0; i < seatsArr[0].length; i++) {
            System.out.printf(" %d", i + 1);
        }
        for (int i = 0; i < rows; i++) {
            System.out.printf("\n%d", i + 1);
            for (int j = 0; j < seats; j++) {
                System.out.printf(" %c", seatsArr[i][j]);
            }
        }
        System.out.print("\n");
    }

    public static void printStats(int soldTickets, final int totalSeats, int curRevenue, int totalRevenue) {
        System.out.printf("Number of purchased tickets: %d\n", soldTickets);
        System.out.printf("Percentage: %.2f%%\n", 100.0 * soldTickets / totalSeats);
        System.out.printf("Current income: $%d\n", curRevenue);
        System.out.printf("Total income: $%d\n", totalRevenue);
    }
}