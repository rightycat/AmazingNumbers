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
    private boolean jumping;
    private ArrayList<Integer> digits = new ArrayList<>();

    void setNumber(long number) {
        this.number = number;
        numberString = String.valueOf(number);
        digits = createDigitsList();
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
        jumping = isJumping();
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
        System.out.printf("\tjumping: %b\n", jumping);
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

    void showSelectedNumbersProperties(long repetitions, String... properties) {
        long elementsShown = 0;

        while (elementsShown < repetitions) {
            try {
                boolean allPropertiesTrue = true;

                for (String property : properties) {
                    if (!isPropertyTrue(property)) {
                        allPropertiesTrue = false;
                        break;  // Exit the loop if any property is not true
                    }
                }

                if (allPropertiesTrue) {
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
        if (jumping) System.out.print(" jumping,");
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
        int digitsSum = 0;
        int digitsProduct = 1;
        for (int number : digits) {
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

    private boolean isJumping() {
        boolean jumping = true;
        int size = digits.size();
        for (int i = 0; i < size; i++) {
            if (size > 1) {
                if (i == 0) {
                    if (!differenceIsOne(digits.get(i), digits.get(i + 1))) {
                        jumping = false;
                    }
                } else if (i == size - 1) {
                    if (!differenceIsOne(digits.get(i), digits.get(i - 1))) {
                        jumping = false;
                    }
                } else {
                    if (!(differenceIsOne(digits.get(i), digits.get(i + 1)) && differenceIsOne(digits.get(i), digits.get(i - 1)))) {
                        jumping = false;
                    }
                }
            }
        }
        return jumping;
    }

    private boolean differenceIsOne(int currentDigit, int neighborDigit) {
        return neighborDigit == currentDigit + 1 || neighborDigit == currentDigit - 1;
    }

    private ArrayList<Integer> createDigitsList() {
        String[] digitString = numberString.split("");
        ArrayList<Integer> digitsList = new ArrayList<>();
        for (String digit : digitString) {
            digitsList.add(Integer.parseInt(digit));
        }
        return digitsList;
    }
}
