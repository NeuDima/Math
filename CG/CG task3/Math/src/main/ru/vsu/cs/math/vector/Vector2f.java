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
    //vector change
    @Override
    public Vector2f add(Vector2f v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    //addition
    //no vector change
    @Override
    public Vector2f sum(Vector2f v) {
        return new Vector2f(this.x + v.x, this.y + v.y);
    }

    //addition
    //vector change
    public Vector2f add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    //addition
    //no vector change
    public Vector2f sum(double x, double y) {
        return new Vector2f(this.x + x, this.y + y);
    }

    //subtraction
    //vector change
    @Override
    public Vector2f sub(Vector2f v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    //subtraction
    //no vector change
    @Override
    public Vector2f dif(Vector2f v) {
        return new Vector2f(this.x - v.x, this.y - v.y);
    }

    //subtraction
    //vector change
    public Vector2f sub(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    //subtraction
    //no vector change
    public Vector2f dif(double x, double y) {
        return new Vector2f(this.x - x, this.y - y);
    }


    //multiplying vector by scalar
    //vector change
    @Override
    public Vector2f mul(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    //multiplying vector by scalar
    //no vector change
    @Override
    public Vector2f prod(double scalar) {
        double x = this.x * scalar;
        double y = this.y * scalar;
        return new Vector2f(x, y);
    }

    //dividing vector by scalar
    //vector change
    @Override
    public Vector2f div(double scalar) {
        if (Math.abs(scalar) < 1e-14) {
            throw new ArithmeticException("You cant divide on 0");
        }
        this.x /= scalar;
        this.y /= scalar;
        return this;
    }

    //dividing vector by scalar
    //no vector change
    @Override
    public Vector2f div1(double scalar) {
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
        this.div1(len);
        return this;
    }

    @Override
    public double dotProduct(Vector2f v) {
        return this.x * v.x + this.y * v.y;
    }

    @Override
    public boolean equals(Vector2f v) {
        return (this.x - v.x) < 1e-14 && (this.y - v.y) < 1e-14;
    }
}
