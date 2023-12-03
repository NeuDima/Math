package main.ru.vsu.cs.math.vector;

public interface IVector<T extends IVector<T>> {

    T add(T vector);

    T sum(T vector);

    T sub(T vector);

    T dif(T vector);

    T mul(double scalar);

    T prod(double scalar);

    T div(double scalar);

    T div1(double scalar);

    double length();

    T normalization();

    double dotProduct(T vector);

    boolean equals(T vector);
}
