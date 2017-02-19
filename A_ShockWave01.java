package R_MyExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A_ShockWave01 {
    public static void main(String[] args) throws IOException {

        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        String[] matrixDimensions = scan.readLine().split(" ");
        int rows = Integer.parseInt(matrixDimensions[0]);
        int cols = Integer.parseInt(matrixDimensions[1]);
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = 0;
            }
        }

        while (true) {
            String[] input = scan.readLine().split(" ");
            if ("Here".equals(input[0])) break;
            int x1 = Integer.parseInt(input[0]);
            int y1 = Integer.parseInt(input[1]);
            int x2 = Integer.parseInt(input[2]);
            int y2 = Integer.parseInt(input[3]);

            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    matrix[i][j]++;
                }
            }
        }

        for (int[] aMatrix : matrix) {
            System.out.println(Arrays.toString(aMatrix).replace("]", "").replace("[", "").replace(", ", " "));
        }
    }
}