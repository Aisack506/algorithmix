package model;

public class TextOperations {

    public static void main(String[] args) {
        System.out.println(isPalindrome("Racecar"));
        System.out.println(isPalindrome("Anita lava la tina"));
    }

    public static boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        String cleanWord = word.replaceAll("[\\W_]", "").toLowerCase();
        return cleanWord.contentEquals(new StringBuilder(cleanWord).reverse());
    }
}
