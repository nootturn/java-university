package edu.course.lab01;

/**
 * Небольшие методы для первой лабораторной работы.
 */
public final class CourseToolkit {

    private CourseToolkit() {
        // Утилитарный класс не должен иметь экземпляров.
    }

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPalindrome(String word) throws IllegalArgumentException {
        if (word == null) {
            throw new IllegalArgumentException("word is empty");
        }

        int wordLen = word.length();

        for (int i = 0; i < wordLen / 2; i++) {
            if (word.charAt(i) != word.charAt(wordLen - 1 - i)){
                return false;
            }
        }

        return true;
    }

    public static double average(int[] values) throws IllegalArgumentException {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("array is empty or null");
        }

        double sum = 0;

        for (int num : values) {
            sum += num;
        }

        return sum / values.length;
    }

    public static int minValueInIntegerArray(int[] arr) {
        int min = arr[0];

        for (int num : arr){
            if (num < min) {
                min = num;
            }
        }

        return min;
    }

    public static int maxValueInIntegerArray(int[] arr) {
        int max = arr[0];

        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
    /**
     * Возвращает true, если число четное.
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
}
