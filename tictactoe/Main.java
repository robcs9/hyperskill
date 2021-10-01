package tictactoe;
import java.util.Scanner;

public class Main {
    // static void printGame(char[][] field);
    // static char nextMove(char[][] field, char player);
    // static boolean gameState(char[][] field);

    public static void main(String[] args) {
        boolean gameOver = false;
        char player = 'O';
        char[][] field = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}}; // Play field initialized
        printGame(field);

        while (!gameOver) {
            player = nextMove(field, player);
            printGame(field);
            gameOver = gameState(field);
        }
    }

    static void printGame(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < field.length; i++) {
            System.out.println(
                    "| " + field[i][0] + " " +
                            field[i][1] + " " +
                            field[i][2] + " |"
            );
        }
        System.out.println("---------");
    }
    static char nextMove (char[][] field, char player) {
        Scanner in = new Scanner(System.in);
        int[] move = new int[2]; // (playerMove) The current player's move placement
        boolean legal = false; // Check for proper player move
        player = player == 'O' ? 'X' : 'O'; // Alternate player turn

        while (!legal) {
            System.out.print("Enter the coordinates: ");
            move[0] = in.nextInt();
            move[1] = in.nextInt();
            move[0]--;
            move[1]--;
            if (move[0] > 2 || move[1] > 2 || move[0] < 0 || move[1] < 0) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (field[move[0]][move[1]] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
            } else if (field[move[0]][move[1]] == ' ') {
                field[move[0]][move[1]] = player; // Field updated with a new move
                legal = true;
            } else {
                System.out.println("You should enter numbers!");
            }
        }
        return player;
    }
    static boolean gameState (char[][] field) {
        boolean finished = false;
        char winner = 'D';

        // Is the game finished?
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == ' ') {
                    finished = false;
                    j = field.length;
                    i = field.length;
                } else {
                    finished = true;
                }
            }
        }
        // Scoring
        if (field[0][0] == 'X' && field[0][1] == 'X' && field[0][2] == 'X' ||
                field[1][0] == 'X' && field[1][1] == 'X' && field[1][2] == 'X' ||
                field[2][0] == 'X' && field[2][1] == 'X' && field[2][2] == 'X' ||
                field[0][0] == 'X' && field[1][0] == 'X' && field[2][0] == 'X' ||
                field[0][1] == 'X' && field[1][1] == 'X' && field[2][1] == 'X' ||
                field[0][2] == 'X' && field[1][2] == 'X' && field[2][2] == 'X' ||
                field[0][0] == 'X' && field[1][1] == 'X' && field[2][2] == 'X' ||
                field[0][2] == 'X' && field[1][1] == 'X' && field[2][0] == 'X') {
            winner = 'X';
            finished = true;
        } else if (field[0][0] == 'O' && field[0][1] == 'O' && field[0][2] == 'O' ||
                field[1][0] == 'O' && field[1][1] == 'O' && field[1][2] == 'O' ||
                field[2][0] == 'O' && field[2][1] == 'O' && field[2][2] == 'O' ||
                field[0][0] == 'O' && field[1][0] == 'O' && field[2][0] == 'O' ||
                field[0][1] == 'O' && field[1][1] == 'O' && field[2][1] == 'O' ||
                field[0][2] == 'O' && field[1][2] == 'O' && field[2][2] == 'O' ||
                field[0][0] == 'O' && field[1][1] == 'O' && field[2][2] == 'O' ||
                field[0][2] == 'O' && field[1][1] == 'O' && field[2][0] == 'O') {
            winner = 'O';
            finished = true;
        }
        if (finished) {
            if (winner == 'D') {
                System.out.println("Draw");
            } else {
                System.out.println(winner + " wins");
            }
        }
        return finished;
    }
}
