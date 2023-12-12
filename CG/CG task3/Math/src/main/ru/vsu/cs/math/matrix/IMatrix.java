package main.ru.vsu.cs.math.matrix;

import main.ru.vsu.cs.math.vector.IVector;

public interface IMatrix<T extends IMatrix<T, V>, V extends IVector<V>> {
    double getValue(int i, int j);

    void setValue(int i, int j, double value);

    boolean equals(T matrix);

    T add(T matrix);

    T sub(T matrix);

    T mulMatrix(T matrix);

    double determinantMatrix();

    T transposition();

    T inverseMatrix();

    V mulVector(V vector);

    V gaussMethod(V vector);
}
