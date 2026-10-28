package model;
import java.util.Map;

public class ConversionOperations {
    public static void main(String[] args) {
        System.out.println(romanToInt("MDCCVI"));
    }

    // --- ROMAN CONVERSIONS ---
    public static String intToRoman(int number) {
        if (number <= 0 || number > 3999) {
            return "Invalid range: Please enter a number between 1 and 3999.";
        }

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                number -= values[i];
                roman.append(symbols[i]);
            }
        }
        return roman.toString();
    }

    public static int romanToInt(String roman) {
        if (roman == null || roman.isEmpty()) {
            throw new IllegalArgumentException("Roman numeral cannot be null or empty.");
        }

        String normalizedRoman = roman.toUpperCase();

        Map<Character, Integer> romanNumbers = Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100,
                'D', 500,
                'M', 1000
        );

        int total = 0;
        for (int i = 0; i < normalizedRoman.length(); i++) {
            char currentChar = normalizedRoman.charAt(i);

            // Validate character
            if (!romanNumbers.containsKey(currentChar)) {
                throw new IllegalArgumentException("All string letters must be valid Roman numerals.");
            }

            int currentVal = romanNumbers.get(currentChar);

            // Look-ahead: if current value is less than the next value, subtract it (e.g., IV, IX)
            if (i + 1 < normalizedRoman.length() && currentVal < romanNumbers.get(normalizedRoman.charAt(i + 1))) {
                total -= currentVal;
            } else {
                total += currentVal;
            }
        }
        return total;
    }

    // --- CONVERSIONS ---
    public static double metersToFeet(double meters) {
        return meters * 3.28084;
    }

    public static double feetToMeters(double feet) {
        return feet / 3.28084;
    }

    public static double kilometersToMiles(double km) {
        return km * 0.621371;
    }

    public static double milesToKilometers(double miles) {
        return miles / 0.621371;
    }

    public static double centimetersToInches(double cm) {
        return cm / 2.54;
    }

    public static double inchesToCentimeters(double inches) {
        return inches * 2.54;
    }

    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5.0 / 9.0;
    }

    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static double kilogramsToPounds(double kg) {
        return kg * 2.20462;
    }

    public static double poundsToKilograms(double pounds) {
        return pounds / 2.20462;
    }

    public static double gramsToOunces(double grams) {
        return grams * 0.035274;
    }

    public static double ouncesToGrams(double ounces) {
        return ounces / 0.035274;
    }

}
