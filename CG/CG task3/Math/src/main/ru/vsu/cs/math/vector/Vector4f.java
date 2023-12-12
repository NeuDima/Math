package main.ru.vsu.cs.math.vector;

public class Vector4f implements IVector<Vector4f>{
    public double x;
    public double y;
    public double z;
    public double w;

    public Vector4f(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4f(double[] arr) {
        if (arr.length != 4) {
            throw new ArithmeticException("Wrong array length to create vector");
        }
        this.x = arr[0];
        this.y = arr[1];
        this.z = arr[2];
        this.w = arr[3];
    }

    @Override
    public double[][] getVector() {
        return new double[][]{{x}, {y}, {z}, {w}};
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

    public double getW() {
        return w;
    }

    //addition
    @Override
    public Vector4f add(Vector4f v) {
        return new Vector4f(this.x + v.x, this.y + v.y, this.z + v.z, this.w + v.w);
    }

    //addition
    public Vector4f add(double x, double y, double z, double w) {
        return new Vector4f(this.x + x, this.y + y, this.z + z, this.w + w);
    }

    //subtraction
    @Override
    public Vector4f sub(Vector4f v) {
        return new Vector4f(this.x - v.x, this.y - v.y, this.z - v.z, this.w - v.w);
    }

    //subtraction
    public Vector4f sub(double x, double y, double z, double w) {
        return new Vector4f(this.x - x, this.y - y, this.z - z, this.w - w);
    }

    //multiplying vector by scalar
    @Override
    public Vector4f mul(double scalar) {
        double x = this.x * scalar;
        double y = this.y * scalar;
        double z = this.z * scalar;
        double w = this.w * scalar;
        return new Vector4f(x, y, z, w);
    }

    //dividing vector by scalar
    @Override
    public Vector4f div(double scalar) {
        if (Math.abs(scalar) < 1e-14) {
            throw new ArithmeticException("You cant divide on 0");
        }
        double x = this.x / scalar;
        double y = this.y / scalar;
        double z = this.z / scalar;
        double w = this.w / scalar;
        return new Vector4f(x, y, z, w);
    }

    //vector length
    @Override
    public double length() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

    //vector normalization (length reduction to 1)
    @Override
    public Vector4f normalization() {
        double len = this.length();
        if (Math.abs(len) < 1e-14) {
            throw new ArithmeticException("This vector cannot be normalized because its length is zero");
        }
        return this.div(len);
    }

    @Override
    public double dotProduct(Vector4f v) {
        return this.x * v.x + this.y * v.y + this.z * v.z + this.w * v.w;
    }

    @Override
    public boolean equals(Vector4f v) {
        return Math.abs(this.x - v.x) < 1e-14 && Math.abs(this.y - v.y) < 1e-14 &&
                Math.abs(this.z - v.z) < 1e-14 && Math.abs(this.w - v.w) < 1e-14;
    }

    @Override
    public String toString() {
        return "Vector4f{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }
}
