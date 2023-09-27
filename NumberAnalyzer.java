package numbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class NumberAnalyzer {

    private BigInteger number;
    private String numberString;

    private void setNumber(BigInteger number) {
        this.number = number;
        numberString = String.valueOf(number);
    }

    public static void requestHandler(NumberAnalyzer numberAnalyzer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        showOptions();
        BigInteger request;
        String requestString;
        do {
            System.out.print("\nEnter a request: ");
            requestString = scanner.nextLine();
            request = new BigInteger(requestString);
            if (request.compareTo(BigInteger.ZERO) > 0) {
                numberAnalyzer.setNumber(request);
                numberAnalyzer.properties();
            } else if (request.compareTo(BigInteger.ZERO) < 0) {
                System.out.println("\nThe first parameter should be a natural number or zero.");
            } else {
                System.out.println("Goodbye!");
            }
        } while (!request.equals(BigInteger.ZERO));
    }

    private static void showOptions() {
        System.out.println("Supported requests: ");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter 0 to exit");
        System.out.println();
    }

    private void properties() {
        boolean even = isEven();
        boolean odd = !even;
        boolean buzz = isBuzzNumber();
        boolean duck = isDuckNumber();
        boolean palindromic = isPalindrome();
        System.out.printf("Properties of %d\n", number);
        System.out.printf("\teven: %b\n", even);
        System.out.printf("\todd: %b\n", odd);
        System.out.printf("\tbuzz: %b\n", buzz);
        System.out.printf("\tduck: %b\n", duck);
        System.out.printf("\tpalindromic: %b\n", palindromic);
    }

    private boolean isEven() {
        return number.mod(BigInteger.TWO).equals(BigInteger.ZERO);
    }

    private boolean isBuzzNumber() {
        return number.mod(BigInteger.valueOf(7)).equals(BigInteger.ZERO) || number.mod(BigInteger.TEN).equals(BigInteger.valueOf(7));
    }

    private boolean isDuckNumber() {
        BigInteger numberCopy = number;
        do {
            if (numberCopy.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
                return true;
            } else {
                numberCopy = numberCopy.divide(BigInteger.TEN);
            }
        } while (!numberCopy.equals(BigInteger.ZERO));
        return false;
    }

    private ArrayList<BigInteger> integerToList() {
        ArrayList<BigInteger> digitList = new ArrayList<>();
        BigInteger numberCopy = number;
        do {
            BigInteger digit = new BigInteger(String.valueOf(numberCopy.mod(BigInteger.valueOf(10))));
            digitList.add(digit); // Add the digit to the beginning of the list
            numberCopy = numberCopy.divide(BigInteger.valueOf(10));
        } while (numberCopy.compareTo(BigInteger.ZERO) != 0);
        return digitList;
    }

    private boolean isPalindrome() {
        ArrayList<BigInteger> digitList = integerToList();
        int lastIndex = digitList.size() - 1;
        for (int i = lastIndex; i >= 0; i--) {
            BigInteger lastDigit = digitList.get(i);
            BigInteger oppositeDigit = digitList.get(lastIndex - i);
            if (!lastDigit.equals(oppositeDigit)) {
                return false;
            }
        }
        return true;
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
