package main.ru.vsu.cs.math.vector;

public interface IVector<T extends IVector<T>> {
    T add(T vector);

    T sub(T vector);

    T mul(float scalar);

    T div(float scalar);

    float length();

    T normalization();

    float dotProduct(T vector);

    boolean equals(T vector);

    String toString();

    float[][] getVector();
}
