import java.util.Scanner;

public class NQueens {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter n for n*n board : ");
        int n = scan.nextInt();
        boolean[][] board = new boolean[n][n];

        System.out.print(nQueen(board, 0));
        System.out.println(" are the number of ways n*n board can be filled with " + n + " queens.");
        scan.close();
    }

    public static int nQueen(boolean[][] board, int row) {
        if (row == board.length) {
            printSolution(board);
            System.out.println();
            return 1;
        }
        // Placing the Queen
        int numOfWays = 0;
        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = true;
                numOfWays += nQueen(board, row + 1);
                board[row][col] = false;
            }
        }
        return numOfWays;
    }

    // checking if is i
    static boolean isSafe(boolean[][] board, int row, int col) {
        // for vertical check
        for (int i = 0; i < row; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        // for upper left
        int upperLeft = Math.min(row, col);
        for (int i = 1; i <= upperLeft; i++) {
            if (board[row - i][col - i]) {
                return false;
            }
        }
        // for Upper right
        int upperRight = Math.min(row, board.length - col - 1);
        for (int i = 1; i <= upperRight; i++) {
            if (board[row - i][col + i]) {
                return false;
            }
        }
        return true;
    }

    static void printSolution(boolean[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j]) {
                    System.out.print("Q ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }
}
