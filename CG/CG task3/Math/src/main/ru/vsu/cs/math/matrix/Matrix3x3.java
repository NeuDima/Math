package main.ru.vsu.cs.math.matrix;

import main.ru.vsu.cs.math.vector.Vector3f;

public class Matrix3x3 implements IMatrix<Matrix3x3, Vector3f> {
    private double[][] matrix = new double[3][3];

    public Matrix3x3(
            double a11, double a12, double a13,
            double a21, double a22, double a23,
            double a31, double a32, double a33) {
        matrix[0][0] = a11;
        matrix[0][1] = a12;
        matrix[0][2] = a13;
        matrix[1][0] = a21;
        matrix[1][1] = a22;
        matrix[1][2] = a23;
        matrix[2][0] = a31;
        matrix[2][1] = a32;
        matrix[2][2] = a33;
    }

    public Matrix3x3(double[] matrix) {
        if (matrix.length != 9) {
            throw new ArithmeticException("Wrong array length to create matrix. The length should be 9");
        }
        this.matrix[0][0] = matrix[0];
        this.matrix[0][1] = matrix[1];
        this.matrix[0][2] = matrix[2];
        this.matrix[1][0] = matrix[3];
        this.matrix[1][1] = matrix[4];
        this.matrix[1][2] = matrix[5];
        this.matrix[2][0] = matrix[6];
        this.matrix[2][1] = matrix[7];
        this.matrix[2][2] = matrix[8];
    }

    public Matrix3x3(double[][] matrix) {
        if (matrix.length != 3) {
            for (int i = 0; i < 3; i++) {
                if (matrix[i].length != 3) {
                    throw new ArithmeticException("Wrong array length to create matrix. The length should be 3x3");
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, 3);
        }
    }

    //creates an empty matrix
    public Matrix3x3 () {
        double[] arr = new double[9];
        new Matrix3x3(arr);
    }

    @Override
    public double getValue(int i, int j) {
        if (i > 2 || i < 0) {
            throw new ArithmeticException("wrong value for 'i'");
        }
        if (j > 2 || j < 0) {
            throw new ArithmeticException("wrong value for 'j'");
        }
        return matrix[i][j];
    }

    @Override
    public void setValue(int i, int j, double value) {
        matrix[i][j] = value;
    }

    private double[][] getArr() {
        return matrix;
    }

    @Override
    public boolean equals(Matrix3x3 matrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Math.abs(this.getValue(i, j) - matrix.getValue(i, j)) > 1e-14) {
                    return false;
                }
            }
        }
        return true;
    }

    //creates an identity matrix
    public static Matrix3x3 identityMatrix() {
        double[][] arr = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    arr[i][j] = 1;
                }
            }
        }
        return new Matrix3x3(arr);
    }

    @Override
    //addition matrix
    public Matrix3x3 add(Matrix3x3 matrix) {
        Matrix3x3 matrix3x3 = new Matrix3x3();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                double value = this.matrix[i][j] + matrix.getValue(i, j);
                matrix3x3.setValue(i, j, value);
            }
        }
        return matrix3x3;
    }

    @Override
    //subtraction matrix
    public Matrix3x3 sub(Matrix3x3 matrix) {
        Matrix3x3 matrix3x3 = new Matrix3x3();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                double value = this.matrix[i][j] - matrix.getValue(i, j);
                matrix3x3.setValue(i, j, value);
            }
        }
        return matrix3x3;
    }

    @Override
    public Matrix3x3 mulMatrix(Matrix3x3 matrix) {
        Matrix3x3 arrResult = new Matrix3x3();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    double value = arrResult.getValue(i, j);
                    value += this.getValue(i, k) * matrix.getValue(k, j);
                    arrResult.setValue(i, j, value);
                }
            }
        }
        return arrResult;
    }

    @Override
    public Vector3f mulVector(Vector3f vector) {
        double[][] arrResult = new double[3][1];
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                arrResult[i][0] += this.getValue(i, k) * vector.getVector()[k][0];
            }
        }
        return new Vector3f(arrResult[0][0], arrResult[1][0], arrResult[2][0]);
    }

    @Override
    public double determinantMatrix() {
        double v1 = this.getValue(0, 0) * this.getValue(1, 1) * this.getValue(2, 2);
        double v2 = this.getValue(0, 1) * this.getValue(1, 2) * this.getValue(2, 0);
        double v3 = this.getValue(0, 2) * this.getValue(1, 0) * this.getValue(2, 1);
        double v4 = this.getValue(0, 2) * this.getValue(1, 1) * this.getValue(2, 0);
        double v5 = this.getValue(0, 1) * this.getValue(1, 0) * this.getValue(2, 2);
        double v6 = this.getValue(0, 0) * this.getValue(1, 2) * this.getValue(2, 1);
        return v1 + v2 + v3 - v4 - v5 - v6;
    }

    //removes a given row and column from a matrix
    private double[][] matrixReduction(double[][] arr, int row, int col) {
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

    //transposition matrix
    @Override
    public Matrix3x3 transposition() {
        Matrix3x3 arrTransposition = new Matrix3x3();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arrTransposition.setValue(j, i, this.getValue(i, j));
            }
        }
        return arrTransposition;
    }

    @Override
    public Matrix3x3 inverseMatrix() {
        double[][] arr = this.getArr();
        double delta = this.determinantMatrix();
        if (delta == 0) {
            return null;
        }
        Matrix3x3 arrTrans = this.transposition();
        double[][] arrInverse = new double[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                double[][] arrReduction = matrixReduction(arrTrans.getArr(), i, j);
                arrInverse[i][j] = Math.pow(-1, i + j) * determinantMatrix2x2(arrReduction) / delta;
            }
        }
        return new Matrix3x3(arrInverse);
    }

    private double determinantMatrix2x2 (double[][] arr) {
        return arr[0][0] * arr[1][1] - arr[1][0] * arr[0][1];
    }

    @Override
    public Vector3f gaussMethod(Vector3f vector) {
        double[][] inputArr = new double[3][4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inputArr[i][j] = this.getValue(i, j);
            }
        }
        for (int i = 0; i < 3; i++) {
            inputArr[i][3] = vector.getVector()[i][0];
        }
        double[] arrOutput = SearchGaussMethod.gaussMethod(inputArr);
        return new Vector3f(arrOutput[0], arrOutput[1], arrOutput[2]);
    }
}
