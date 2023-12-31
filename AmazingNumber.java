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
    private boolean happy;
    private boolean sad;
    private ArrayList<Integer> digits = new ArrayList<>();

    public AmazingNumber(long number) {
        setNumber(number);
        setProperties();
    }

    void setNumber(long number) {
        this.number = number;
        numberString = String.valueOf(number);
        digits = createDigitsList();
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
        happy = isHappy();
        sad = !happy;
    }

    // Determine if a specific field from this object is true or false
    boolean isPropertyFalse(String propertyName) throws IllegalAccessException {
        // Reflective field used to access object properties
        try {
            Field field = getClass().getDeclaredField(propertyName.toLowerCase());
            return !((boolean) field.get(this));
        } catch (NoSuchFieldException e) {
            return true;
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
                int currentDigit = digits.get(i);
                int nextDigit;
                int previousDigit;
                if (i == 0) {
                    nextDigit = digits.get(i + 1);
                    if (!differenceIsOne(currentDigit, nextDigit)) {
                        jumping = false;
                    }
                } else if (i == size - 1) {
                    previousDigit = digits.get(i - 1);
                    if (!differenceIsOne(currentDigit, previousDigit)) {
                        jumping = false;
                    }
                } else {
                    previousDigit = digits.get(i - 1);
                    nextDigit = digits.get(i + 1);
                    if (!(differenceIsOne(currentDigit, nextDigit) && differenceIsOne(currentDigit, previousDigit))) {
                        jumping = false;
                    }
                }
            }
        }
        return jumping;
    }

    private boolean isHappy() {
        long originalNumber = this.number;
        if (number == 1) {
            return true;
        }

        ArrayList<Integer> sequence = new ArrayList<>();
        boolean happyNumber = false;
        boolean hasSequence = false;

        while (!happyNumber && !hasSequence) {
            int digitsSquareSum = 0;
            for (int digit : digits) {
                digitsSquareSum += (int) Math.pow(digit, 2);
            }
            if (!sequence.contains(digitsSquareSum)) {
                if (digitsSquareSum == 1) {
                    happyNumber = true;
                } else {
                    sequence.add(digitsSquareSum);
                    setNumber(digitsSquareSum);
                }
            } else {
                hasSequence = true;
            }
        }
        setNumber(originalNumber);
        return happyNumber;
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

    public long getNumber() {
        return number;
    }

    public boolean getEven() {
        return even;
    }

    public boolean getOdd() {
        return odd;
    }

    public boolean getBuzz() {
        return buzz;
    }

    public boolean getDuck() {
        return duck;
    }

    public boolean getSpy() {
        return spy;
    }

    public boolean getPalindromic() {
        return palindromic;
    }

    public boolean getGapful() {
        return gapful;
    }

    public boolean getSunny() {
        return sunny;
    }

    public boolean getSquare() {
        return square;
    }

    public boolean getJumping() {
        return jumping;
    }

    public boolean getHappy() {
        return happy;
    }

    public boolean getSad() {
        return sad;
    }
}
