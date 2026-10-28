package model;

public class GeometryAndAlgebra {

    public static void main(String[] args) {

    }

    public static String classifyTriangle(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return "Invalid input: Side lengths must be greater than zero.";
        }

        if ((a + b <= c) || (a + c <= b) || (b + c <= a)) {
            return "Not a valid triangle: The sum of any two sides must be greater than the third side.";
        }

        if (a == b && b == c) {
            return "Equilateral Triangle (All sides are equal)";
        } else if (a == b || b == c || a == c) {
            return "Isosceles Triangle (Two sides are equal)";
        } else {
            return "Scalene Triangle (All sides are different)";
        }
    }

    public static String solveLinear(double a, double b) {
        if (a == 0) {
            throw new ArithmeticException("Coefficient 'a' cannot be zero in a linear equation.");
        }
        return "x = " + String.format("%.2f", -b / a);
    }

    public static String[] solveQuadratic(double a, double b, double c) {
        if (a == 0) {
            return new String[] { "Not quadratic. Linear root x = " + (-c / b) };
        }

        double discriminant = (b * b) - (4 * a * c);

        if (discriminant > 0) {
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return new String[] {
                    "x1 = " + String.format("%.2f", x1),
                    "x2 = " + String.format("%.2f", x2)
            };
        } else if (discriminant == 0) {
            double x = -b / (2 * a);
            return new String[] { "x = " + String.format("%.2f", x) };
        } else {
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
            return new String[] {
                    "x1 = " + String.format("%.2f", realPart) + " + " + String.format("%.2f", imaginaryPart) + "i",
                    "x2 = " + String.format("%.2f", realPart) + " - " + String.format("%.2f", imaginaryPart) + "i"
            };
        }
    }
}
