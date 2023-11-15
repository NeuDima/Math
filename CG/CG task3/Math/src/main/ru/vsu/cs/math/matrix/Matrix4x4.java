package main.ru.vsu.cs.math.matrix;

import main.ru.vsu.cs.math.vector.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;

public class Matrix4x4 implements IMatrix<Matrix4x4> {
    private double[][] matrix = new double[4][4];

    public Matrix4x4(double a11, double a12, double a13, double a14, double a21, double a22, double a23, double a24,
                     double a31, double a32, double a33, double a34, double a41, double a42, double a43, double a44) {
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
        this.matrix[1][4] = matrix[7];
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
        this.matrix = matrix;
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

    @Override
    public double[][] getArr() {
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

    private double determinantMatrix3x3() {
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
        double delta = this.determinantMatrix3x3();
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

    public double[][] gaussMethod(Vector4f vector) {
        double[][] inputArr = new double[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                inputArr[i][j] = this.getValue(i, j);
            }
        }
        for (int i = 0; i < 4; i++) {
            inputArr[i][4] = vector.getVector()[i][0];
        }
        for (int i = 0; i < inputArr.length - 1; i++) {
            for (int j = i; j < inputArr.length - 1; j++) {
                if (inputArr[i][i] != 0) {
                    double a = inputArr[j + 1][i] / inputArr[i][i];
                    for (int k = 0; k < inputArr[0].length; k++) {
                        inputArr[j + 1][k] -= a * inputArr[i][k];
                    }
                }
            }
        }

        ArrayList<Integer> nullLine = nullCheck(inputArr);
        ArrayList<Double> listOut = new ArrayList<>();
        if (nullLine.isEmpty()) {
            listOut = variableSubstitution(inputArr);

            double[][] arrOut = new double[4][1];
            for (int i = listOut.size() - 1; i >= 0; i--) {
                arrOut[3 - i][0] = listOut.get(i);
            }
            return arrOut;
        } else {
            inputArr = movingNullLines(inputArr, nullLine, 0);
            double[][][] arr = solution(inputArr, nullLine.size());
            inputArr = arr[0];
            System.out.println(Arrays.deepToString(arr));

            double[] arrX = arr[1][0];

            ArrayList<Double> outInvert = variableSubstitution(inputArr);

            for (int i = 0; i < outInvert.size(); i++) {
                listOut.add(outInvert.get(outInvert.size() - 1 - i));
            }

            for (double x : arrX) {
                listOut.add(x);
            }


        }

        double[][] arrOut = new double[4][1];
        for (int i = 0; i < arrOut.length; i++) {
            arrOut[i][0] = listOut.get(i);
        }
        return arrOut;
    }

    private ArrayList<Double> variableSubstitution(double[][] inputArr) {
        int len = inputArr.length;
        double x = inputArr[len - 1][len] / inputArr[len - 1][len - 1];

        double[][] arr = new double[len - 1][len];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (j != arr[i].length - 1) {
                    arr[i][j] = inputArr[i][j];
                } else {
                    arr[i][j] = inputArr[i][j + 1] - x * inputArr[i][j];
                }
            }
        }

        ArrayList<Double> output = new ArrayList<>();
        output.add(x);

        if (arr.length <= 1) {
            output.add(arr[0][1] / arr[0][0]);
            return output;
        }

        ArrayList<Double> outNew = variableSubstitution(arr);
        output.addAll(outNew);

        return output;
    }

    private ArrayList<Integer> nullCheck (double[][] arr) {
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] == 0) {
                    count++;
                    if (count == arr.length) {
                        out.add(i);
                    }
                }
            }
        }
        return out;
    }

    private double[][] movingNullLines (double[][] arr, ArrayList<Integer> nullLine, int number) {
        double[] arrCopy = new double[arr.length + 1];
        System.arraycopy(arr[nullLine.get(number)], 0, arrCopy, 0, arr.length + 1);

        if (nullLine.get(number) != arr.length - 1) {
            for (int i = nullLine.get(number); i < arr.length; i++) {
                System.arraycopy(arr[nullLine.get(number + 1)], 0, arr[nullLine.get(number)], 0, arr.length);
            }
            System.arraycopy(arrCopy, 0, arr[arr.length - 1], 0, arr.length + 1);

            if (nullLine.size() != number + 1) {
                arr = movingNullLines(arr, nullLine, number + 1);
            }
        }
        return arr;
    }

    private double[][][] solution (double[][] arr, int count) {
        double[] arrX = new double[count];
        double[][] arrSolution = new double[arr.length][arr[0].length];

        for (int i = 0; i < arrSolution.length; i++) {
            System.arraycopy(arr[i], 0, arrSolution[i], 0, arrSolution[0].length);
        }

        for (int x = 0; x < count; x++) {
            arrX[arrX.length - x - 1] = x;

            for (int i = 0; i < arrSolution.length - x; i++) {
                for (int j = 0; j < arrSolution[0].length - x; j++) {
                    if (j == arrSolution[0].length - 2 - x) {
                        arrSolution[i][j] = arrSolution[i][j + 1] - x * arrSolution[i][j];
                    }
                }
            }
        }

        double[][] arrOut  = new double[arr.length - count][arr.length + 1 - count];
        for (int i = 0; i < arrOut.length; i++) {
            System.arraycopy(arrSolution[i], 0, arrOut[i], 0, arrOut[0].length);
        }
        return new double[][][]{arrOut, {arrX}};
    }
}
