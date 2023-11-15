package test.ru.vsu.cs.math.vector;

import main.ru.vsu.cs.math.vector.Vector2f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector2fTest {
    @Test
    void add_vector() {
        Vector2f v1 = new Vector2f(1, 2);
        Vector2f v2 = new Vector2f(4, 8);
        Vector2f v = new Vector2f(5, 10);
        Assertions.assertTrue(v.equals(v1.add(v2)));
    }

    @Test
    void add_params() {
        Vector2f v1 = new Vector2f(1, 2);
        double x = 3;
        double y = 7;
        Vector2f v = new Vector2f(4, 9);
        Assertions.assertTrue(v.equals(v1.add(x, y)));
    }

    @Test
    void sub_vector() {
        Vector2f v1 = new Vector2f(1, 2);
        Vector2f v2 = new Vector2f(4, 8);
        Vector2f v = new Vector2f(-3, -6);
        Assertions.assertTrue(v.equals(v1.sub(v2)));
    }

    @Test
    void sub_params() {
        Vector2f v1 = new Vector2f(1, 2);
        double x = 3;
        double y = 7;
        Vector2f v = new Vector2f(-2, -5);
        Assertions.assertTrue(v.equals(v1.sub(x, y)));
    }

    @Test
    void mul() {
        Vector2f v1 = new Vector2f(1, 2);
        double scalar = 3;
        Vector2f v = new Vector2f(3, 6);
        Assertions.assertTrue(v.equals(v1.mul(scalar)));
    }

    @Test
    void div_scalarNotEqualsZero() {
        Vector2f v1 = new Vector2f(6, 8);
        double scalar = 4;
        Vector2f v = new Vector2f(1.5, 2);
        Assertions.assertTrue(v.equals(v1.div(scalar)));
    }

    @Test
    void div_scalarEqualsZero() {
        Vector2f v = new Vector2f(2, 4);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> v.div(0));
        Assertions.assertTrue(exception.getMessage().contains("You cant divide on 0"));
    }

    @Test
    void length() {
        Vector2f v1 = new Vector2f(6, 8);
        Assertions.assertEquals(10, v1.length());
    }

    @Test
    void normalization_vectorLengthNotEqualsZero() {
        Vector2f v1 = new Vector2f(6, 8);
        Vector2f v = new Vector2f(0.6, 0.8);
        Assertions.assertTrue(v.equals(v1.normalization()));
    }

    @Test
    void normalization_vectorLengthEqualsZero() {
        Vector2f v1 = new Vector2f(0, 0);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, v1::normalization);
        Assertions.assertTrue(exception.getMessage().contains("This vector cannot be normalized because its length is zero"));
    }

    @Test
    void dotProduct() {
        Vector2f v1 = new Vector2f(2, -5);
        Vector2f v2 = new Vector2f(-1, 0);
        Assertions.assertEquals(-2, v1.dotProduct(v2));
    }
}
