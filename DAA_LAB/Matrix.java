import java.util.Arrays;
import java.util.Scanner;
public class Matrix {
     static Scanner in= new Scanner(System.in);
    public static void main(String[] args) {

        int[][] a = {
                {8,2,5,3},
                {5,7,9,1},
                {2,4,3,1},
                {3,1,9,2}
        };
        int[][] b = {
                {2,2,6,4},
                {4,7,5,1},
                {3,7,3,2},
                {7,4,5,6}
        };
        System.out.println("Matrix A =>");
        for (int i = 0; i < a.length; i++) {
            System.out.println(Arrays.toString(a[i]));
        }
        System.out.println();
        System.out.println("Matrix B =>");
        for (int i = 0; i < b.length; i++) {
            System.out.println(Arrays.toString(b[i]));
        }
        System.out.println();
        System.out.println("Matrix after Multiplication resultMatrix =>");
        int[][] resultMatrix = matrixMultiplyDivideConquer(a,b);
        for (int i = 0; i < resultMatrix.length; i++) {
            System.out.println(Arrays.toString(resultMatrix[i]));
        }
    }
    
    public static int[][] matrixMultiplyDivideConquer(int[][] A, int[][] B) {
        int n = A.length;

        // If the matrices are 1x1, just do a simple multiplication
        if (n == 1) {
            int[][] C = new int[1][1];
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }

        // Split matrices into quarters
        int size = n / 2;
        int[][] a11 = new int[size][size];
        int[][] a12 = new int[size][size];
        int[][] a21 = new int[size][size];
        int[][] a22 = new int[size][size];
        int[][] b11 = new int[size][size];
        int[][] b12 = new int[size][size];
        int[][] b21 = new int[size][size];
        int[][] b22 = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                a11[i][j] = A[i][j];
                a12[i][j] = A[i][j + size];
                a21[i][j] = A[i + size][j];
                a22[i][j] = A[i + size][j + size];
                b11[i][j] = B[i][j];
                b12[i][j] = B[i][j + size];
                b21[i][j] = B[i + size][j];
                b22[i][j] = B[i + size][j + size];
            }
        }
        // Recursively compute products
        int[][] p1 = matrixMultiplyDivideConquer(a11, b11);
        int[][] p2 = matrixMultiplyDivideConquer(a12, b21);
        int[][] p3 = matrixMultiplyDivideConquer(a11, b12);
        int[][] p4 = matrixMultiplyDivideConquer(a12, b22);
        int[][] p5 = matrixMultiplyDivideConquer(a21, b11);
        int[][] p6 = matrixMultiplyDivideConquer(a22, b21);
        int[][] p7 = matrixMultiplyDivideConquer(a21, b12);
        int[][] p8 = matrixMultiplyDivideConquer(a22, b22);

        // Compute result matrix
        int[][] C = new int[n][n];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                C[i][j] = p1[i][j] + p2[i][j];
                C[i][j + size] = p3[i][j] + p4[i][j];
                C[i + size][j] = p5[i][j] + p6[i][j];
                C[i + size][j + size] = p7[i][j] + p8[i][j];
            }
        }

        return C;
    }

}
