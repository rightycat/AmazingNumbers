package numbers;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class AmazingNumber {

    // Store the current number being analyzed
    private long number;
    private String numberString;

    // Flags indicating various properties of the current number
    private boolean even;
    private boolean odd;
    private boolean buzz;
    private boolean duck;
    private boolean palindromic;
    private boolean gapful;
    private boolean spy;
    private boolean square;
    private boolean sunny;

    void setNumber(long number) {
        this.number = number;
        numberString = String.valueOf(number);
        setProperties();
    }

    private void setProperties() {
        even = isEven();
        odd = !even;
        buzz = isBuzz();
        duck = isDuck();
        palindromic = isPalindrome();
        gapful = isGapful();
        spy = isSpy();
        square = isSquare();
        sunny = isSunny();
    }

    // Show all properties for one number
    void showNumberProperties() {
        System.out.printf("Properties of %d\n", number);
        System.out.printf("\tbuzz: %b\n", buzz);
        System.out.printf("\tduck: %b\n", duck);
        System.out.printf("\tpalindromic: %b\n", palindromic);
        System.out.printf("\tgapful: %b\n", gapful);
        System.out.printf("\tspy: %b\n", spy);
        System.out.printf("\tsquare: %b\n", square);
        System.out.printf("\tsunny: %b\n", sunny);
        System.out.printf("\teven: %b\n", even);
        System.out.printf("\todd: %b\n", odd);
    }

    // Show all properties for multiple numbers
    void showMultipleNumbersProperties(long repetitions) {
        for (int i = 0; i < repetitions; i++) {
            compactProperties();
            setNumber(++number);
        }
    }

    // Show all properties for multiple numbers that have a specified property
    void showSelectedNumbersProperties(long repetitions, String firstProperty) {
        long elementsShown = 0;
        while (elementsShown < repetitions) {
            try {
                if (isPropertyTrue(firstProperty)) {
                    compactProperties();
                    elementsShown++;
                }
                setNumber(++number);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void showSelectedNumbersProperties(long repetitions, String firstProperty, String secondProperty) {
        long elementsShown = 0;
        while (elementsShown < repetitions) {
            try {
                if (isPropertyTrue(firstProperty) && isPropertyTrue(secondProperty)) {
                    compactProperties();
                    elementsShown++;
                }
                setNumber(++number);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Compact format to print properties
    private void compactProperties() {
        System.out.printf("\t%d is ", number);
        if (buzz) System.out.print(" buzz,");
        if (duck) System.out.print(" duck,");
        if (palindromic) System.out.print(" palindromic,");
        if (gapful) System.out.print(" gapful,");
        if (spy) System.out.print(" spy,");
        if (square) System.out.print(" square,");
        if (sunny) System.out.print(" sunny,");
        if (even) System.out.print(" even");
        else System.out.print(" odd");
        System.out.print("\n");
    }

    // Determine if a specific field from this object is true or false
    private boolean isPropertyTrue(String propertyName) throws NoSuchFieldException, IllegalAccessException {
        // Reflective field used to access object properties
        try {
            Field field = getClass().getDeclaredField(propertyName.toLowerCase());
            return (boolean) field.get(this);
        } catch (NoSuchFieldException e) {
            NumberAnalyzer.printPropertyError();
            return false;
        }
    }

    // Series of functions that analyze a particular property
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

    private boolean isGapful() {
        if (numberString.length() >= 3) {
            String lastDigit = String.valueOf(number % 10);
            char firstDigit = numberString.charAt(0);
            long concatNumber = Long.parseLong(firstDigit + lastDigit);
            return number % concatNumber == 0;
        } else return false;

    }

    private boolean isSpy() {
        ArrayList<Long> digitsList = createDigitsList();
        long digitsSum = 0;
        long digitsProduct = 1;
        for (long number : digitsList) {
            digitsSum += number;
            digitsProduct *= number;
        }
        return digitsSum == digitsProduct;
    }

    private boolean isSquare() {
        double sqrRoot = Math.sqrt(number);
        long sqrRootInteger = (long) sqrRoot;
        return sqrRoot == sqrRootInteger;
    }

    private boolean isSquare(long number) {
        double sqrRoot = Math.sqrt(number);
        long sqrRootInteger = (long) sqrRoot;
        return sqrRoot == sqrRootInteger;
    }

    private boolean isSunny() {
        return isSquare(number + 1);
    }

    private ArrayList<Long> createDigitsList() {
        String[] digitString = numberString.split("");
        ArrayList<Long> digitsList = new ArrayList<>();
        for (String digit : digitString) {
            digitsList.add(Long.parseLong(digit));
        }
        return digitsList;
    }
}
