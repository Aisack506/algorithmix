package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class NumberTheory {

    public static boolean isEven(BigInteger number) {
        return number.remainder(BigInteger.valueOf(2)).equals(BigInteger.ZERO);
    }

    public static BigInteger calculateFactorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative.");
        }

        BigInteger result = BigInteger.ONE;
        for (int i = number; i > 1; i--) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        System.out.println(result + "-" + number);
        return result;
    }

    public static List<Integer> calculateFibonacci(int max) {
        List<Integer> fibonacci = new ArrayList<>(List.of(0));

        int a = 0;
        int c = 1;
        for (int i = 0; i < max - 1; i++) {
            int result = a + c;
            a = c;
            c = result;

            fibonacci.add(result);
        }

        return fibonacci;
    }

    public static boolean isLeapYear(int year) {
        // Normal year
        if (String.valueOf(year).length() != 4) {
            throw new IllegalArgumentException("Invalid year");
        }
        if (year % 4 == 0 && year % 100 != 0) {
            return  true;
            // Century (1900, 1800, etc)
        } else return (year % 400 == 0 && year % 100 == 0);
    }

    public static boolean isPrime(int number) {
        if (number <= 1) throw new IllegalArgumentException("Invalid number");
        for (int i = 2; i < Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;

    }

    public static boolean isCapicua(String number) {
        String numberStr = number.charAt(0) != '-' ? number.trim() : number.substring(1).trim() ;
        return numberStr.contentEquals(new StringBuilder(numberStr).reverse());
    }

    public static boolean isYearPuzzle(int number) {
        return (number / 100) + (number % 100) == (number / 10) % 100;
    }
}
