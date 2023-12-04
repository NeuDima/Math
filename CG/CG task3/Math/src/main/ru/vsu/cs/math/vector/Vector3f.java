package main.ru.vsu.cs.math.vector;

public class Vector3f implements IVector<Vector3f>{
    public double x;
    public double y;
    public double z;

    public Vector3f(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double[][] getVector() {
        return new double[][]{{x}, {y}, {z}};
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    //addition
    @Override
    public Vector3f add(Vector3f v) {
        return new Vector3f(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    //addition
    public Vector3f add(double x, double y, double z) {
        return new Vector3f(this.x + x, this.y + y, this.z + z);
    }

    //subtraction
    @Override
    public Vector3f sub(Vector3f v) {
        return new Vector3f(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    //subtraction
    public Vector3f sub(double x, double y, double z) {
        return new Vector3f(this.x - x, this.y - y, this.z - z);
    }

    //multiplying vector by scalar
    @Override
    public Vector3f mul(double scalar) {
        double x = this.x * scalar;
        double y = this.y * scalar;
        double z = this.z * scalar;
        return new Vector3f(x, y, z);
    }

    //dividing vector by scalar
    @Override
    public Vector3f div(double scalar) {
        if (Math.abs(scalar) < 1e-14) {
            throw new ArithmeticException("You cant divide on 0");
        }
        double x = this.x / scalar;
        double y = this.y / scalar;
        double z = this.z / scalar;
        return new Vector3f(x, y, z);
    }

    //vector length
    @Override
    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    //vector normalization (length reduction to 1)
    @Override
    public Vector3f normalization() {
        double len = this.length();
        if (Math.abs(len) < 1e-14) {
            throw new ArithmeticException("This vector cannot be normalized because its length is zero");
        }
        return this.div(len);
    }

    @Override
    public double dotProduct(Vector3f v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    public Vector3f vectorProduct(Vector3f v) {
        double x = this.y * v.z - this.z * v.y;
        double y = this.z * v.x - this.x * v.z;
        double z = this.x * v.y - this.y * v.x;
        return new Vector3f(x, y, z);
    }

    @Override
    public boolean equals(Vector3f v) {
        return Math.abs(this.x - v.x) < 1e-14 && Math.abs(this.y - v.y) < 1e-14 &&
                Math.abs(this.z - v.z) < 1e-14;
    }

    @Override
    public String toString() {
        return "Vector3f{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
