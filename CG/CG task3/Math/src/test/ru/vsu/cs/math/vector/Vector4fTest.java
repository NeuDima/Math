package test.ru.vsu.cs.math.vector;

import main.ru.vsu.cs.math.vector.Vector4f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector4fTest {
    @Test
    void add_vector() {
        Vector4f v1 = new Vector4f(1, 2, 5, 4);
        Vector4f v2 = new Vector4f(4, 8, 7, -1);
        Vector4f v = new Vector4f(5, 10, 12, 3);
        Assertions.assertTrue(v.equals(v1.add(v2)));
    }

    @Test
    void add_params() {
        Vector4f v1 = new Vector4f(1, 2, 5, 4);
        double x = 3;
        double y = 7;
        double z = 2;
        double w = -9;
        Vector4f v = new Vector4f(4, 9, 7, -5);
        Assertions.assertTrue(v.equals(v1.add(x, y, z, w)));
    }

    @Test
    void sub_vector() {
        Vector4f v1 = new Vector4f(1, 2, 5, 4);
        Vector4f v2 = new Vector4f(4, 8, 7, -1);
        Vector4f v = new Vector4f(-3, -6, -2, 5);
        Assertions.assertTrue(v.equals(v1.sub(v2)));
    }

    @Test
    void sub_params() {
        Vector4f v1 = new Vector4f(1, 2, 5, 4);
        double x = 4;
        double y = 8;
        double z = 7;
        double w = -1;
        Vector4f v = new Vector4f(-3, -6, -2, 5);
        Assertions.assertTrue(v.equals(v1.sub(x, y, z, w)));
    }

    @Test
    void mul() {
        Vector4f v1 = new Vector4f(1, 2, 5, 4);
        double scalar = 3;
        Vector4f v = new Vector4f(3, 6, 15, 12);
        Assertions.assertTrue(v.equals(v1.mul(scalar)));
    }

    @Test
    void mulX() {
        Vector4f v1 = new Vector4f(0.1, 0.2, 0.3, -0.4);
        Vector4f v2 = v1.mul(3).mul(3).add(new Vector4f(0.1, 0.2, 0.3, -0.4));
        Vector4f v = new Vector4f(1, 2, 3, -4);
        Assertions.assertTrue(v.equals(v2));
    }

    @Test
    void div_scalarNotEqualsZero2() {
        Vector4f v1 = new Vector4f(1, 2, 5, 8);
        double scalar = -4;
        Vector4f v = new Vector4f(-0.25, -0.5, -1.25, -2);
        Assertions.assertTrue(v.equals(v1.div(scalar)));
    }

    @Test
    void div_scalarNotEqualsZero() {
        Vector4f v1 = new Vector4f(3, 6, 15, 12);
        double scalar = 3;
        Vector4f v = new Vector4f(1, 2, 5, 4);
        Assertions.assertTrue(v.equals(v1.div(scalar)));
    }

    @Test
    void div_scalarEqualsZero() {
        Vector4f v = new Vector4f(3, 6, 15, 12);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> v.div(0));
        Assertions.assertTrue(exception.getMessage().contains("You cant divide on 0"));
    }

    @Test
    void length() {
        Vector4f v1 = new Vector4f(1, 2, 5, 4);
        Assertions.assertEquals(Math.sqrt(1 + 4 + 25 + 16), v1.length());
    }

    @Test
    void normalization_vectorLengthNotEqualsZero() {
        Vector4f v1 = new Vector4f(1, 2, 5, 4);
        Vector4f v = new Vector4f(1 / Math.sqrt(1 + 4 + 25 + 16), 2 / Math.sqrt(1 + 4 + 25 + 16),
                5 / Math.sqrt(1 + 4 + 25 + 16), 4 / Math.sqrt(1 + 4 + 25 + 16));
        Assertions.assertTrue(v.equals(v1.normalization()));
    }

    @Test
    void normalization_vectorLengthEqualsZero() {
        Vector4f v1 = new Vector4f(0, 0, 0, 0);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, v1::normalization);
        Assertions.assertTrue(exception.getMessage().contains("This vector cannot be normalized because its length is zero"));
    }

    @Test
    void dotProduct() {
        Vector4f v1 = new Vector4f(1, 2, -4, 10);
        Vector4f v2 = new Vector4f(6, -1, 2, 3);
        Assertions.assertEquals(26, v1.dotProduct(v2));
    }
}
