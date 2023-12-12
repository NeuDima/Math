package main.ru.vsu.cs.math.vector;

public class Vector2f implements IVector<Vector2f> {
    public double x;
    public double y;

    public Vector2f(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(double[] arr) {
        if (arr.length != 2) {
            throw new ArithmeticException("Wrong array length to create vector");
        }
        this.x = arr[0];
        this.y = arr[1];
    }

    /*
     * Получение вектора в виде массива столбца
     */
    @Override
    public double[][] getVector() {
        return new double[][]{{x}, {y}};
    }

    /*
     * Получение значения "X"
     */
    public double getX() {
        return x;
    }

    /*
     * Получение значения "Y"
     */
    public double getY() {
        return y;
    }

    /*
     * Сложение векторов
     * Исходный вектор не изменяется
     * Возвращается новый вектор
     */
    @Override
    public Vector2f add(Vector2f v) {
        return new Vector2f(this.x + v.x, this.y + v.y);
    }

    /*
     * Сложение векторов
     * Исходный вектор не изменяется
     * Возвращается новый вектор
     */
    public Vector2f add(double x, double y) {
        return new Vector2f(this.x + x, this.y + y);
    }

    /*
     * Разница векторов
     * Исходный вектор не изменяется
     * Возвращается новый вектор
     */
    @Override
    public Vector2f sub(Vector2f v) {
        return new Vector2f(this.x - v.x, this.y - v.y);
    }

    /*
     * Разница векторов
     * Исходный вектор не изменяется
     * Возвращается новый вектор
     */
    public Vector2f sub(double x, double y) {
        return new Vector2f(this.x - x, this.y - y);
    }

    /*
     * Произведение вектора на число
     * Исходный вектор не изменяется
     * Возвращается новый вектор
     */
    @Override
    public Vector2f mul(double scalar) {
        double x = this.x * scalar;
        double y = this.y * scalar;
        return new Vector2f(x, y);
    }

    /*
     * Деление вектора на число
     * Исходный вектор не изменяется
     * Возвращается новый вектор
     */
    @Override
    public Vector2f div(double scalar) {
        if (Math.abs(scalar) < 1e-14) {
            throw new ArithmeticException("You cant divide on 0");
        }
        double x = this.x / scalar;
        double y = this.y / scalar;
        return new Vector2f(x, y);
    }

    /*
     * Получение длины вектора
     */
    @Override
    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    /*
     * Нормализация вектора
     */
    @Override
    public Vector2f normalization() {
        double len = this.length();
        if (len < 1e-14) {
            throw new ArithmeticException("This vector cannot be normalized because its length is zero");
        }
        return this.div(len);
    }

    /*
     * Скалярное произведение векторов
     */
    @Override
    public double dotProduct(Vector2f v) {
        return this.x * v.x + this.y * v.y;
    }

    /*
     * Сравнение векторов
     */
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
