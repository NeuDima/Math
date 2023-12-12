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

    /*
     * Создание нулевой матрицы (все значения равны 0)
     */
    public Matrix4x4() {
        double[] arr = new double[16];
        new Matrix4x4(arr);
    }

    /*
     * Получение элемента матрицы по номеру строки(i) и столбца(j)
     */
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

    /*
     * Замена элемента матрицы на новый(value) по номеру строки(i) и столбца(j)
     */
    @Override
    public void setValue(int i, int j, double value) {
        matrix[i][j] = value;
    }

    private double[][] getArr() {
        return matrix;
    }

    /*
     * Сравнение исходной матрицы с новой(matrix)
     */
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

    /*
     * Создание единичной матрицы
     */
    public static Matrix4x4 identityMatrix() {
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

    /*
     * Сложение матриц
     * Исходная матрица изменяется
     */
    @Override
    public Matrix4x4 add(Matrix4x4 matrix) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.matrix[i][j] += matrix.getValue(i, j);
            }
        }
        return this;
    }

    /*
     * Вычитание матрицы из исходной
     * Исходная матрица изменяется
     */
    @Override
    public Matrix4x4 sub(Matrix4x4 matrix) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.matrix[i][j] -= matrix.getValue(i, j);
            }
        }
        return this;
    }

    /*
     * Произведение матриц
     * Исходная матрица не изменяется
     * Возвращается новая матрица
     */
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

    /*
     * Произведение матрицы на вектор
     * Исходная матрица не изменяется
     * Возвращается новый вектор
     */
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

    /*
     * Детерминант матрицы
     */
    @Override
    public double determinantMatrix() {
        double[][] arr = this.getArr();
        return Search.detMatrix(arr);
    }

    /*
     * Транспонирование матрицы
     * Исходная матрица не изменяется
     */
    @Override
    public Matrix4x4 transposition() {
        Matrix4x4 arrTransposition = new Matrix4x4();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arrTransposition.setValue(j, i, this.getValue(i, j));
            }
        }
        return arrTransposition;
    }

    /*
     * Получение обратной матрицы
     * Исходная матрица не изменяется
     */
    @Override
    public Matrix4x4 inverseMatrix() {
        double[][] arr = this.getArr();
        double delta = Search.detMatrix(arr);
        if (delta == 0) {
            return null;
        }
        Matrix4x4 arrTrans = this.transposition();
        double[][] arrInverse = new double[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                double[][] arrReduction = Search.matrixReduction(arrTrans.getArr(), i, j);
                arrInverse[i][j] = Math.pow(-1, i + j) * Search.detMatrix(arrReduction) / delta;
            }
        }
        return new Matrix4x4(arrInverse);
    }

    /*
     * Получение корней системы с помощью метода Гаусса
     * На вход нужен вектор правой части системы
     * Исходная матрица не изменяется
     */
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
