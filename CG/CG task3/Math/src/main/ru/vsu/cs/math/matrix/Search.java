package main.ru.vsu.cs.math.matrix;

public class Search {
    /*
     * Создание нового массива без заданной строки и столбца
     * Исходный массив не изменяется
     */
    protected static double[][] matrixReduction(double[][] arr, int row, int col) {
        int countRow = 0, countCol = 0;
        int len = arr.length;
        double[][] det = new double[len - 1][len - 1];

        for (int i = 0; i < len; i++) {
            if (i == row) {
                countRow++;
                continue;
            }
            for (int j = 0; j < len; j++) {
                if (j == col) {
                    countCol++;
                    continue;
                }
                det[i - countRow][j - countCol] = arr[i][j];
            }
            countCol--;
        }
        return det;
    }

    /*
     * Детерминант массива любого размера
     */
    protected static double detMatrix(double[][] arr) {
        double sum = 0;
        if (arr.length == 1) {
            return arr[0][0];
        } else if (arr.length == 2) {
            return arr[0][0] * arr[1][1] - arr[1][0] * arr[0][1];
        } else {
            for (int i = 0; i < arr.length; i++) {
                double[][] arrMatrix = matrixReduction(arr, i, 0);
                double arrDeterminant = detMatrix(arrMatrix);
                double minor = (arr[i][0] * Math.pow(-1, i) * arrDeterminant);
                sum += minor;
            }
        }
        return sum;
    }
}
