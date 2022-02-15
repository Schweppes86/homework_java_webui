package org.geekbrains.lesson4;

import org.geekbrains.TriangleClass;
import org.geekbrains.TriangleException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TriangleTest {
    @Test
    public void calculateAreaTest() throws TriangleException {
        TriangleClass triangle = new TriangleClass();
        Assertions.assertEquals(0.4330127018922193,triangle.areaOfTriangle(1, 1, 1));
        Assertions.assertEquals(0.9682458365518543,triangle.areaOfTriangle(2, 2, 1));

    }

    @Test
    public void getExceptionFromIncorrectValueTest() {
        TriangleClass triangle = new TriangleClass();
        Assertions.assertThrows(TriangleException.class,()-> triangle.areaOfTriangle(1, 0, 1));
        Assertions.assertThrows(TriangleException.class,()-> triangle.areaOfTriangle(-5,-5, 1));
    }
}
