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

    public double[][] getVector() {
        return new double[][]{{x}, {y}, {z}, {w}};
    }
    //addition vector
    @Override
    public Vector4f add(Vector4f v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
        this.w += v.w;
        return this;
    }

    //addition of vector and parameters
    public Vector4f add(double x, double y, double z, double w) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += w;
        return this;
    }

    //subtraction vector
    @Override
    public Vector4f sub(Vector4f v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
        this.w -= v.w;
        return this;
    }

    //subtraction from the parameter vector
    public Vector4f sub(double x, double y, double z, double w) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        this.w -= w;
        return this;
    }

    //multiplying vector by scalar
    @Override
    public Vector4f mul(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        this.w *= scalar;
        return this;
    }

    //dividing vector by scalar
    @Override
    public Vector4f div(double scalar) {
        if (scalar < Math.pow(10, -9)) {
            throw new ArithmeticException("You cant divide on 0");
        }
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
        this.w /= scalar;
        return this;
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
        if (len == 0) {
            throw new ArithmeticException("This vector cannot be normalized because its length is zero");
        }
        this.div(len);
        return this;
    }

    @Override
    public double dotProduct(Vector4f v) {
        return this.x * v.x + this.y * v.y + this.z * v.z + this.w * v.w;
    }

    @Override
    public boolean equals(Vector4f v) {
        return this.x == v.x && this.y == v.y && this.z == v.z && this.w == v.w;
    }
}
