package main.ru.vsu.cs.math.matrix;

import main.ru.vsu.cs.math.vector.Vector4f;

public class Matrix4x4 implements IMatrix<Matrix4x4, Vector4f> {
    private double[][] matrix = new double[4][4];

    public Matrix4x4(
            double a11, double a12, double a13, double a14,
            double a21, double a22, double a23, double a24,
            double a31, double a32, double a33, double a34,
            double a41, double a42, double a43, double a44) {
        matrix[0][0] = a11;
        matrix[0][1] = a12;
        matrix[0][2] = a13;
        matrix[0][3] = a14;
        matrix[1][0] = a21;
        matrix[1][1] = a22;
        matrix[1][2] = a23;
        matrix[1][3] = a24;
        matrix[2][0] = a31;
        matrix[2][1] = a32;
        matrix[2][2] = a33;
        matrix[2][3] = a34;
        matrix[3][0] = a41;
        matrix[3][1] = a42;
        matrix[3][2] = a43;
        matrix[3][3] = a44;
    }

    public Matrix4x4(double[] matrix) {
        if (matrix.length != 16) {
            throw new ArithmeticException("Wrong array length to create matrix. The length should be 16");
        }
        this.matrix[0][0] = matrix[0];
        this.matrix[0][1] = matrix[1];
        this.matrix[0][2] = matrix[2];
        this.matrix[0][3] = matrix[3];
        this.matrix[1][0] = matrix[4];
        this.matrix[1][1] = matrix[5];
        this.matrix[1][2] = matrix[6];
        this.matrix[1][3] = matrix[7];
        this.matrix[2][0] = matrix[8];
        this.matrix[2][1] = matrix[9];
        this.matrix[2][2] = matrix[10];
        this.matrix[2][3] = matrix[11];
        this.matrix[3][0] = matrix[12];
        this.matrix[3][1] = matrix[13];
        this.matrix[3][2] = matrix[14];
        this.matrix[3][3] = matrix[15];
    }

    public Matrix4x4(double[][] matrix) {
        if (matrix.length != 4) {
            for (int i = 0; i < 4; i++) {
                if (matrix[i].length != 4) {
                    throw new ArithmeticException("Wrong array length to create matrix. The length should be 4x4");
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, 4);
        }
        //this.matrix = matrix;
    }

    //creates an empty matrix
    public Matrix4x4() {
        double[] arr = new double[16];
        new Matrix4x4(arr);
    }

    @Override
    public double getValue(int i, int j) {
        if (i > 3 || i < 0) {
            throw new ArithmeticException("wrong value for 'i'");
        }
        if (j > 3 || j < 0) {
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
    public boolean equals(Matrix4x4 matrix) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.getValue(i, j) != matrix.getValue(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    //creates an identity matrix
    public Matrix4x4 identityMatrix() {
        double[][] arr = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    arr[i][j] = 1;
                }
            }
        }
        return new Matrix4x4(arr);
    }

    @Override
    //addition matrix
    public Matrix4x4 add(Matrix4x4 matrix) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.matrix[i][j] += matrix.getValue(i, j);
            }
        }
        return this;
    }

    @Override
    //subtraction matrix
    public Matrix4x4 sub(Matrix4x4 matrix) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.matrix[i][j] -= matrix.getValue(i, j);
            }
        }
        return this;
    }

    @Override
    public Matrix4x4 mulMatrix(Matrix4x4 matrix) {
        Matrix4x4 arrResult = new Matrix4x4();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    double value = arrResult.getValue(i, j);
                    value += this.getValue(i, k) * matrix.getValue(k, j);
                    arrResult.setValue(i, j, value);
                }
            }
        }
        return arrResult;
    }

    @Override
    public Vector4f mulVector(Vector4f vector) {
        double[][] arrResult = new double[4][1];

        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                arrResult[i][0] += this.getValue(i, k) * vector.getVector()[k][0];
            }
        }
        return new Vector4f(arrResult[0][0], arrResult[1][0], arrResult[2][0], arrResult[3][0]);
    }

    @Override
    public double determinantMatrix() {
        double[][] arr = this.getArr();
        return detMatrix(arr);
    }

    private double detMatrix(double[][] arr) {
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

    @Override
    //transposition matrix
    public Matrix4x4 transposition() {
        Matrix4x4 arrTransposition = new Matrix4x4();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arrTransposition.setValue(j, i, this.getValue(i, j));
            }
        }
        return arrTransposition;
    }

    @Override
    public Matrix4x4 inverseMatrix() {
        double[][] arr = this.getArr();
//        double delta = this.determinantMatrix3x3();
        double delta = detMatrix(arr);
        if (delta == 0) {
            return null;
        }
        Matrix4x4 arrTrans = this.transposition();
        double[][] arrInverse = new double[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                double[][] arrReduction = matrixReduction(arrTrans.getArr(), i, j);
                arrInverse[i][j] = Math.pow(-1, i + j) * detMatrix(arrReduction) / delta;
            }
        }
        return new Matrix4x4(arrInverse);
    }

    @Override
    public Vector4f gaussMethod(Vector4f vector) {
        double[][] inputArr = new double[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                inputArr[i][j] = this.getValue(i, j);
            }
        }
        for (int i = 0; i < 4; i++) {
            inputArr[i][4] = vector.getVector()[i][0];
        }
        double[] arrOutput = SearchGaussMethod.gaussMethod(inputArr);
        return new Vector4f(arrOutput[0], arrOutput[1], arrOutput[2], arrOutput[3]);
    }
}
