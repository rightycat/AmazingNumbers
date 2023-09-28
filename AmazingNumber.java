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
    }

    static void showOptions() {
        System.out.println("Supported requests: ");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit");
        System.out.println();
    }

    static void showAvailableProperties() {
        StringBuilder allEnums = new StringBuilder("Available properties: [");
        for (AvailableProperties property : AvailableProperties.values()) {
            allEnums.append(property.name()).append(", ");
        }
        System.out.println(allEnums.append("]"));
    }

    // Show all properties for one number
    void showNumberProperties() {
        System.out.printf("Properties of %d\n", number);
        System.out.printf("\tbuzz: %b\n", buzz);
        System.out.printf("\tduck: %b\n", duck);
        System.out.printf("\tpalindromic: %b\n", palindromic);
        System.out.printf("\tgapful: %b\n", gapful);
        System.out.printf("\tspy: %b\n", spy);
        System.out.printf("\teven: %b\n", even);
        System.out.printf("\todd: %b\n", odd);
    }

    // Show all properties for multiple numbers
    void showMultipleNumbersProperties(long numOfNumbers) {
        for (int i = 0; i < numOfNumbers; i++) {
            compactProperties();
            setNumber(number + 1);
        }
    }

    // Show all properties for multiple numbers that have a specified property
    void showSelectedNumbersProperties(long secondArgument, String requestArgument) {
        long elementsShown = 0;
        while (elementsShown < secondArgument) {
            try {
                if (isPropertyTrue(requestArgument)) {
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
        if (even) System.out.print(" even");
        else System.out.print(" odd");
        System.out.print("\n");
    }

    // Determine if a specific field from this object is true or false
    private boolean isPropertyTrue(String propertyName) throws NoSuchFieldException, IllegalAccessException {
        // Reflective field used to access object properties
        Field field = getClass().getDeclaredField(propertyName.toLowerCase());
        return (boolean) field.get(this);
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

    private ArrayList<Long> createDigitsList() {
        String[] digitString = numberString.split("");
        ArrayList<Long> digitsList = new ArrayList<>();
        for (String digit : digitString) {
            digitsList.add(Long.parseLong(digit));
        }
        return digitsList;
    }
}
