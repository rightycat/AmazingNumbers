package numbers;

import java.math.BigInteger;
import java.util.Scanner;

public class NumberAnalyzer {

    private long number;
    private String numberString;

    private void setNumber(long number) {
        this.number = number;
        numberString = String.valueOf(number);
    }

    public static void requestHandler(NumberAnalyzer numberAnalyzer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        showOptions();
        long request;
        String requestString;
        do {
            System.out.print("\nEnter a request: ");
            requestString = scanner.nextLine();
            request = Long.parseLong(requestString);
            if (request > 0) {
                numberAnalyzer.setNumber(request);
                numberAnalyzer.showProperties();
            } else if (request < 0) {
                System.out.println("\nThe first parameter should be a natural number or zero.");
            } else {
                System.out.println("Goodbye!");
            }
        } while (request != 0);
    }

    private static void showOptions() {
        System.out.println("Supported requests: ");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter 0 to exit");
        System.out.println();
    }

    private void showProperties() {
        boolean even = isEven();
        boolean odd = !even;
        boolean buzz = isBuzz();
        boolean duck = isDuck();
        boolean palindromic = isPalindrome();
        System.out.printf("Properties of %d\n", number);
        System.out.printf("\teven: %b\n", even);
        System.out.printf("\todd: %b\n", odd);
        System.out.printf("\tbuzz: %b\n", buzz);
        System.out.printf("\tduck: %b\n", duck);
        System.out.printf("\tpalindromic: %b\n", palindromic);
    }

    private boolean isEven() {
        return number % 2 == 0;
    }

    private boolean isBuzz() {
        return number % 7 == 0 || number % 10 == 7;
    }

    private boolean isDuck() {
        return numberString.contains("0");
    }

    private boolean isPalindrome() {
        return numberString.contentEquals(new StringBuilder(numberString).reverse());
    }

    /*private void explanation() {
        boolean divisibleBy7 = number.mod(BigInteger.valueOf(7)).equals(BigInteger.ZERO);
        boolean lastDigitIs7 = number.mod(BigInteger.TEN).equals(BigInteger.valueOf(7));
        if (!divisibleBy7 && !lastDigitIs7) {
            System.out.printf("%d is neither divisible by 7 nor does it end with 7.", number);
        } else if (divisibleBy7 && lastDigitIs7) {
            System.out.printf("%d is divisible by 7 and ends with 7.", number);
        } else if (divisibleBy7) {
            System.out.printf("%d is divisible by 7.", number);
        } else {
            System.out.printf("%d ends with 7.", number);
        }
    }*/
}
