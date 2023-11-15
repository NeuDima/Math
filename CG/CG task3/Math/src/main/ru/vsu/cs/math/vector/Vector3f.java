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

    public double get(int num) {
        if (num == 0) {
            return x;
        } else if (num == 1) {
            return y;
        } else if (num == 2) {
            return z;
        } else {
            throw new ArithmeticException("Invalid item number");
        }
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

    //addition vector
    @Override
    public Vector3f add(Vector3f v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
        return this;
    }

    //addition of vector and parameters
    public Vector3f add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    //subtraction vector
    @Override
    public Vector3f sub(Vector3f v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
        return this;
    }

    //subtraction from the parameter vector
    public Vector3f sub(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    //multiplying vector by scalar
    @Override
    public Vector3f mul(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        return this;
    }

    //dividing vector by scalar
    @Override
    public Vector3f div(double scalar) {
        if (scalar < Math.pow(10, -9)) {
            throw new ArithmeticException("You cant divide on 0");
        }
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
        return this;
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
        if (len == 0) {
            throw new ArithmeticException("This vector cannot be normalized because its length is zero");
        }
        this.div(len);
        return this;
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
        return this.x == v.x && this.y == v.y && this.z == v.z;
    }
}
