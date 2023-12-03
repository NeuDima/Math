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
    //vector change
    @Override
    public Vector4f add(Vector4f v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
        this.w += v.w;
        return this;
    }

    //addition
    //no vector change
    @Override
    public Vector4f sum(Vector4f v) {
        return new Vector4f(this.x + v.x, this.y + v.y, this.z + v.z, this.w + v.w);
    }

    //addition
    //vector change
    public Vector4f add(double x, double y, double z, double w) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += w;
        return this;
    }

    //addition
    //no vector change
    public Vector4f sum(double x, double y, double z, double w) {
        return new Vector4f(this.x + x, this.y + y, this.z + z, this.w + w);
    }

    //subtraction
    //vector change
    @Override
    public Vector4f sub(Vector4f v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
        this.w -= v.w;
        return this;
    }

    //subtraction
    //no vector change
    @Override
    public Vector4f dif(Vector4f v) {
        return new Vector4f(this.x - v.x, this.y - v.y, this.z - v.z, this.w - v.w);
    }

    //subtraction
    //vector change
    public Vector4f sub(double x, double y, double z, double w) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        this.w -= w;
        return this;
    }

    //subtraction
    //no vector change
    public Vector4f dif(double x, double y, double z, double w) {
        return new Vector4f(this.x - x, this.y - y, this.z - z, this.w - w);
    }

    //multiplying vector by scalar
    //vector change
    @Override
    public Vector4f mul(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        this.w *= scalar;
        return this;
    }

    //multiplying vector by scalar
    //no vector change
    @Override
    public Vector4f prod(double scalar) {
        double x = this.x * scalar;
        double y = this.y * scalar;
        double z = this.z * scalar;
        double w = this.w * scalar;
        return new Vector4f(x, y, z, w);
    }

    //dividing vector by scalar
    //vector change
    @Override
    public Vector4f div(double scalar) {
        if (Math.abs(scalar) < 1e-14) {
            throw new ArithmeticException("You cant divide on 0");
        }
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
        this.w /= scalar;
        return this;
    }

    //dividing vector by scalar
    //no vector change
    @Override
    public Vector4f div1(double scalar) {
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
        this.div1(len);
        return this;
    }

    @Override
    public double dotProduct(Vector4f v) {
        return this.x * v.x + this.y * v.y + this.z * v.z + this.w * v.w;
    }

    @Override
    public boolean equals(Vector4f v) {
        return (this.x - v.x) < 1e-14 && (this.y - v.y) < 1e-14 && (this.z - v.z) < 1e-14 && (this.w - v.w) < 1e-14;
    }
}
