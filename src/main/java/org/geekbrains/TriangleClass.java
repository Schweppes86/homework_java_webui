package org.geekbrains;

public class TriangleClass {

    public double areaOfTriangle (int a, int b, int c) throws TriangleException {
        checkTriangle(a,b,c);
        return calculateTriangleArea(a,b,c);
    }

    private boolean checkTriangle(int a, int b, int c) throws TriangleException {
        if (a <= 0 || b <= 0 || c <= 0) {throw new TriangleException("Incorrect Value one of the side!");}
        if ((a + b <= c ) || (a + c <= b) || (b + c <= a) ) {throw new TriangleException("Sum of two side equals or more than other side!");}
        return true;
    }

    private double calculateTriangleArea(int a, int b, int c) {
        double p = (a+b+c) / 2.0;
        double s = Math.sqrt(p*(p-a)*(p-b)*(p-c));
        System.out.println("Площадь треугольника равна " + s);
        return s;
    }


}
