package numbers;
import java.util.ArrayList;
import java.util.List;

public class AmazingNumberGenerator {

    public static List<AmazingNumber> generateSingleNumber(long startValue) {
        AmazingNumber number = new AmazingNumber(startValue);
        List<AmazingNumber> numbersList = new ArrayList<>();
        numbersList.add(number);
        return numbersList;
    }

    public static List<AmazingNumber> generateMultipleNumbers(long startValue, long repetitions) {
        AmazingNumber number = new AmazingNumber(startValue);
        List<AmazingNumber> numbersList = new ArrayList<>();
        for (int i = 0; i < repetitions; i++) {
            numbersList.add(number);
            number = new AmazingNumber(number.getNumber() + 1);
        }
        return numbersList;
    }

    public static List<AmazingNumber> generateSelectedNumbersDEV(long startValue, long repetitions, ArrayList<String> targetProperties, ArrayList<String> excludedProperties)
            throws IllegalAccessException {
        AmazingNumber number = new AmazingNumber(startValue);
        List<AmazingNumber> numbersList = new ArrayList<>();
        int elementsAdded = 0;

        while (elementsAdded < repetitions) {
            boolean allPropertiesValid = true;

            for (String property : targetProperties) {
                if (number.isPropertyFalse(property)) {
                    allPropertiesValid = false;
                    break;  // Exit the loop if any property is not true
                }
            }

            for (String property : excludedProperties) {
                if (!number.isPropertyFalse(property)) {
                    allPropertiesValid = false;
                    break;
                }
            }

            if (allPropertiesValid) {
                numbersList.add(number);
                elementsAdded++;
            }

            number = new AmazingNumber(number.getNumber() + 1);
        }

        return numbersList;
    }
}
