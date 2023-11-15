package main.ru.vsu.cs.math.vector;

public class Vector2f implements IVector<Vector2f> {
    public double x;
    public double y;

    public Vector2f(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double[][] getVector() {
        return new double[][]{{x}, {y}};
    }
    //addition vector
    @Override
    public Vector2f add(Vector2f v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    //addition of vector and parameters
    public Vector2f add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    //subtraction vector
    @Override
    public Vector2f sub(Vector2f v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    //subtraction from the parameter vector
    public Vector2f sub(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    //multiplying vector by scalar
    @Override
    public Vector2f mul(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    //dividing vector by scalar
    @Override
    public Vector2f div(double scalar) {
        if (scalar < Math.pow(10, -9)) {
            throw new ArithmeticException("You cant divide on 0");
        }
        this.x /= scalar;
        this.y /= scalar;
        return this;
    }

    //vector length
    @Override
    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    //vector normalization (length reduction to 1)
    @Override
    public Vector2f normalization() {
        double len = this.length();
        if (len == 0) {
            throw new ArithmeticException("This vector cannot be normalized because its length is zero");
        }
        this.div(len);
        return this;
    }

    @Override
    public double dotProduct(Vector2f v) {
        return this.x * v.x + this.y * v.y;
    }

    @Override
    public boolean equals(Vector2f v) {
        return this.x == v.x && this.y == v.y;
    }
}
