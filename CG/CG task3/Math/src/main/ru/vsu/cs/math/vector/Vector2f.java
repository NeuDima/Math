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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    //addition
    @Override
    public Vector2f add(Vector2f v) {
        return new Vector2f(this.x + v.x, this.y + v.y);
    }

    //addition
    public Vector2f add(double x, double y) {
        return new Vector2f(this.x + x, this.y + y);
    }

    //subtraction
    @Override
    public Vector2f sub(Vector2f v) {
        return new Vector2f(this.x - v.x, this.y - v.y);
    }

    //subtraction
    public Vector2f sub(double x, double y) {
        return new Vector2f(this.x - x, this.y - y);
    }

    //multiplying vector by scalar
    @Override
    public Vector2f mul(double scalar) {
        double x = this.x * scalar;
        double y = this.y * scalar;
        return new Vector2f(x, y);
    }

    //dividing vector by scalar
    @Override
    public Vector2f div(double scalar) {
        if (Math.abs(scalar) < 1e-14) {
            throw new ArithmeticException("You cant divide on 0");
        }
        double x = this.x / scalar;
        double y = this.y / scalar;
        return new Vector2f(x, y);
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
        if (len < 1e-14) {
            throw new ArithmeticException("This vector cannot be normalized because its length is zero");
        }
        return this.div(len);
    }

    @Override
    public double dotProduct(Vector2f v) {
        return this.x * v.x + this.y * v.y;
    }

    @Override
    public boolean equals(Vector2f v) {
        return Math.abs(this.x - v.x) < 1e-14 && Math.abs(this.y - v.y) < 1e-14;
    }

    @Override
    public String toString() {
        return "Vector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
