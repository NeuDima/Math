package main.ru.vsu.cs.math.matrix;

public interface IMatrix<T extends IMatrix<T>> {
    double getValue(int i, int j);

    void setValue(int i, int j, double value);

    double[][] getArr();

    boolean equals(T matrix);

    T identityMatrix();

    T add(T matrix);

    T sub(T matrix);

    T mulMatrix(T matrix);

    double determinantMatrix();

    T transposition();

    T inverseMatrix();
}
