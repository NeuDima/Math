package main.ru.vsu.cs.math.vector;

public interface IVector<T extends IVector<T>> {
    T add(T vector);

    T sub(T vector);

    T mul(double scalar);

    T div(double scalar);

    double length();

    T normalization();

    double dotProduct(T vector);

    boolean equals(T vector);

    String toString();

    double[][] getVector();
}
